package site.licsber.notice.repository.memobird;

import org.springframework.data.mongodb.repository.MongoRepository;
import site.licsber.notice.model.memobird.UserBind;

public interface UserBindRepository extends MongoRepository<UserBind, String> {

    UserBind findByMemoBirdID(String memoBirdID);

    <S extends UserBind> S save(S userBind);

    void deleteByMemoBirdID(String memoBirdID);

    void deleteAll();
    
    void delete(UserBind userBind);

}
