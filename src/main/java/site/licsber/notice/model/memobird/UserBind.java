package site.licsber.notice.model.memobird;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import site.licsber.notice.util.DateUtils;
import site.licsber.notice.util.MemoBirdUtils;

@Data
@Document(collection = "bind")
public class UserBind {

    @Id
    @Field("mId")
    @NonNull
    private String memoBirdID;

    private boolean suc;

    @Field("uI")
    private String userIdentifying = MemoBirdUtils.randomUserIdentify();

    @Field("t")
    private String timestamp = DateUtils.getNowTimestamp();

    @Field("uId")
    private String userID;

}
