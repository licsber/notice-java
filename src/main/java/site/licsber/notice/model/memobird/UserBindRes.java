package site.licsber.notice.model.memobird;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserBindRes {

    @JsonProperty("showapi_res_code")
    private int showApiResCode;
    @JsonProperty("showapi_res_error")
    private String showApiResError;
    @JsonProperty("showapi_userid")
    private String showApiUserId;

}
