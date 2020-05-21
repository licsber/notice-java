package site.licsber.notice.service.memobird;

import site.licsber.notice.model.memobird.UserBind;

public interface UserBindService {

    UserBind saveUserBind(UserBind userBind);

    UserBind findUserBindByMemoBirdId(String memoBirdId);

    void deleteUserBindByMemoBirdId(String memoBirdId);

    void deleteUserBind(UserBind userBind);

}
