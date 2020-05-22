package site.licsber.notice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import site.licsber.notice.model.memobird.UserBind;
import site.licsber.notice.service.impl.memobird.SetUserBindServiceImpl;
import site.licsber.notice.util.MemoBirdUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@V1RestController
public class MemoBirdController {

    private final SetUserBindServiceImpl setUserBindService;

    public MemoBirdController(SetUserBindServiceImpl setUserBindService) {
        this.setUserBindService = setUserBindService;
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/bind")
    public UserBind bind(@RequestParam String memoBirdId, HttpServletResponse response, UserBind userBind) {
        userBind.setMemoBirdID(memoBirdId);
        if (!MemoBirdUtils.isMemoBirdIdValid(memoBirdId)) {
            userBind.setStatus("MemoBirdId不合法.");
            return userBind;
        }
        Cookie cookie = new Cookie("memoBirdId", memoBirdId);
        cookie.setMaxAge(365 * 24 * 60 * 60);
        response.addCookie(cookie);
        return setUserBindService.setUserBind(userBind);
    }

}
