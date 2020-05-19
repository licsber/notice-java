package site.licsber.notice.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String getTimestamp(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    public static String getTimestamp(long timestamp) {
        return getTimestamp(new Date(timestamp * 1000));
    }

    public static String getNowTimestamp() {
        return getTimestamp(new Date());
    }

}
