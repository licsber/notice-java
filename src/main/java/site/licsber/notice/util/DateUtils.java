package site.licsber.notice.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss",
                    Locale.CHINA).withZone(ZoneId.of("Asia/Shanghai"));

    public static String getTimestamp(Instant instant) {
        return formatter.format(instant);
    }

    public static String getTimestamp(Date date) {
        // https://stackoverflow.com/questions/25376242/java8-java-util-date-conversion-to-java-time-zoneddatetime
        return getTimestamp(date.toInstant());
    }

    public static String getTimestampSec(long timestampSec) {
        return getTimestamp(Instant.ofEpochSecond(timestampSec));
    }

    public static String getTimestampMil(long timestampMil) {
        return getTimestamp(Instant.ofEpochMilli(timestampMil));
    }

    public static String getNowTimestamp() {
        return getTimestamp(Instant.now());
    }

}
