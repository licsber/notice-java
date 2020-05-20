package site.licsber.notice.memobird;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import site.licsber.notice.model.memobird.MemoBirdConfig;

@SpringBootTest
public class MemoBirdConfigTests {
    @Autowired
    private MemoBirdConfig memoBirdConfig;

    @Test
    void testConfigRead() {
        Assertions.assertNotNull(memoBirdConfig.getAk());
        Assertions.assertEquals(32, memoBirdConfig.getAk().length());
    }

}
