package site.licsber.notice.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemoBirdUtilsTests {

    @Test
    void isMemoBirdIdValid() {
        Assertions.assertFalse(MemoBirdUtils.isMemoBirdIdValid("123"));
        Assertions.assertFalse(MemoBirdUtils.isMemoBirdIdValid("aaba7901a443aaa"));
        Assertions.assertFalse(MemoBirdUtils.isMemoBirdIdValid("Aaba7901a443aaaa"));
        Assertions.assertFalse(MemoBirdUtils.isMemoBirdIdValid("@aba7901a443aaaa"));
        Assertions.assertFalse(MemoBirdUtils.isMemoBirdIdValid("aasadfasdfsadfsdfaaa"));
        Assertions.assertFalse(MemoBirdUtils.isMemoBirdIdValid("aaba7901a443a-aa"));
        Assertions.assertFalse(MemoBirdUtils.isMemoBirdIdValid(""));
        Assertions.assertFalse(MemoBirdUtils.isMemoBirdIdValid(null));

        Assertions.assertTrue(MemoBirdUtils.isMemoBirdIdValid("aaba7901a443aaaa"));
    }

    @Test
    void randomUserIdentify() {
        Assertions.assertEquals(12, MemoBirdUtils.randomUserIdentify().length());
    }

}
