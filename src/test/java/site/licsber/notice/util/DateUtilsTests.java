package site.licsber.notice.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DateUtilsTests {

    @Test
    void getTimestamp() {
        Assertions.assertEquals("2020-05-19 11:34:09", DateUtils.getTimestamp(1589859249));
    }

    @Test
    void getNowTimestamp() {
        System.out.println(DateUtils.getNowTimestamp());
    }

}
