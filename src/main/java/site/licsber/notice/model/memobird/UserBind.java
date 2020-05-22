package site.licsber.notice.model.memobird;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;
import site.licsber.notice.util.DateUtils;
import site.licsber.notice.util.MemoBirdUtils;

@Data
// https://docs.spring.io/spring-data/data-mongodb/docs/current/reference/html/#mongo-template.type-mapping
@TypeAlias("bind")
@NoArgsConstructor
@RequiredArgsConstructor
@Document(collection = "bind")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserBind {

    @MongoId
    @NonNull
    private String memoBirdID;

    private boolean suc;

    @Field("uI")
    private String userIdentifying = MemoBirdUtils.randomUserIdentify();

    @Field("t")
    private String timestamp = DateUtils.getNowTimestamp();

    @Field("uId")
    private String userID;

    @Transient
    private String status;

}
