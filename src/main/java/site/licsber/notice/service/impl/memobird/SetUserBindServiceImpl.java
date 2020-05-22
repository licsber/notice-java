package site.licsber.notice.service.impl.memobird;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import site.licsber.notice.model.memobird.MemoBirdConfig;
import site.licsber.notice.model.memobird.UserBind;
import site.licsber.notice.model.memobird.UserBindRes;
import site.licsber.notice.repository.memobird.UserBindRepository;
import site.licsber.notice.service.memobird.SetUserBindService;
import site.licsber.notice.util.DateUtils;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class SetUserBindServiceImpl implements SetUserBindService {

    private final MemoBirdConfig config;

    private final UserBindRepository userBindRepository;

    @SuppressWarnings("UnstableApiUsage")
    private final RateLimiter rateLimiter = RateLimiter.create(0.5);

    public SetUserBindServiceImpl(MemoBirdConfig config, UserBindRepository userBindRepository) {
        this.config = config;
        this.userBindRepository = userBindRepository;
    }

    @SuppressWarnings("UnstableApiUsage")
    private UserBind retrieveUserId(UserBind userBind) {
        Map<String, String> req = new HashMap<>();
        req.put("ak", config.getAk());
        req.put("timestamp", DateUtils.getNowTimestamp());
        req.put("memobirdID", userBind.getMemoBirdID());
        req.put("useridentifying", userBind.getUserIdentifying());

        RestTemplate template = new RestTemplate();

        if (!rateLimiter.tryAcquire()) {
            userBindRepository.delete(userBind);
            userBind.setStatus("请求过于频繁.");
        } else {
            UserBindRes res = template.postForObject(url, req, UserBindRes.class);
            log.debug("res: " + res);

            if (res == null) {
                log.warn("MemoBird server error");
                userBindRepository.delete(userBind);
                userBind.setStatus("对方服务器错误.");
            } else if (res.getShowApiResCode() == 1) {
                userBind.setSuc(true);
                userBind.setUserID(res.getShowApiUserId());
                userBind.setStatus("成功获取UserId.");
                log.info("suc for UserBind " + userBind);
                userBindRepository.save(userBind);
            } else {
                log.info("err for UserBind " + res);
                userBindRepository.delete(userBind);
                userBind.setStatus(res.getShowApiResError());
            }
        }

        return userBind;
    }

    @Override
    public UserBind setUserBind(UserBind userBind) {
        UserBind tmp = userBindRepository.findByMemoBirdID(userBind.getMemoBirdID());
        if (tmp != null && tmp.isSuc()) {
            log.info("UserBind exists " + tmp);
            return tmp;
        }
        return retrieveUserId(userBind);
    }

}
