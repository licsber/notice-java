package site.licsber.notice.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private SetUserBindServiceImpl setUserBindService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/bind")
    public UserBind bind(@RequestParam String memoBirdId, HttpServletResponse response) {
        if (!MemoBirdUtils.isMemoBirdIdValid(memoBirdId)) {
            return null;
        }
        Cookie cookie = new Cookie("memoBirdId", memoBirdId);
        response.addCookie(cookie);
        UserBind userBind = new UserBind();
        userBind.setMemobirdID(memoBirdId);
        return setUserBindService.setUserBind(userBind);
    }

}
