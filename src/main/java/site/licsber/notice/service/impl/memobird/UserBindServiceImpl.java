package site.licsber.notice.service.impl.memobird;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import site.licsber.notice.model.memobird.UserBind;
import site.licsber.notice.service.memobird.UserBindService;
import site.licsber.notice.util.MemoBirdUtils;

@Service
public class UserBindServiceImpl implements UserBindService {

    private final MongoTemplate template;

    public UserBindServiceImpl(MongoTemplate template) {
        this.template = template;
    }

    @Override
    public void deleteUserBindByMemoBirdId(String memoBirdId) {
        Query query = new Query(Criteria.where("mId").is(memoBirdId));
        template.findAllAndRemove(query, UserBind.class);
    }

    @Override
    public void deleteUserBind(UserBind userBind) {
        if (userBind.getMemobirdID() != null) {
            deleteUserBindByMemoBirdId(userBind.getMemobirdID());
        }
    }

    @Override
    public UserBind saveUserBind(UserBind userBind) {
        if (MemoBirdUtils.isMemoBirdIdValid(userBind.getMemobirdID())) {
            return template.save(userBind);
        }
        return null;
    }

    @Override
    public UserBind findUserBindByMemoBirdId(String memoBirdId) {
        Query query = new Query(Criteria.where("mId").is(memoBirdId));
        return template.findOne(query, UserBind.class);
    }

}
