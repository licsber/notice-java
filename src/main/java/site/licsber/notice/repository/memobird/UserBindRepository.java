package site.licsber.notice.repository.memobird;

import com.mongodb.lang.NonNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import site.licsber.notice.model.memobird.UserBind;

@Repository
public interface UserBindRepository extends MongoRepository<UserBind, String> {

    UserBind findByMemoBirdID(String memoBirdID);

    @NonNull
    <S extends UserBind> S save(@NonNull S userBind);

    void deleteByMemoBirdID(String memoBirdID);

    void deleteAll();

    void delete(@NonNull UserBind userBind);

}
