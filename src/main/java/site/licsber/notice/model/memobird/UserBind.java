package site.licsber.notice.model.memobird;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import site.licsber.notice.util.DateUtils;

@Data
@Document(collection = "bind")
public class UserBind {

    @Id
    private String id;
    private boolean suc;
    @Field("mId")
    @Indexed
    private String memobirdID;
    @Field("t")
    private String timestamp = DateUtils.getNowTimestamp();
    @Field("uId")
    private String userID;

}
