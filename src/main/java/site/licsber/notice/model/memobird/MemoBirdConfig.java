package site.licsber.notice.model.memobird;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "config.memo-bird")
public class MemoBirdConfig {

    private String ak;

}
