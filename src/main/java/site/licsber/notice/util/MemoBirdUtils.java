package site.licsber.notice.util;

import java.util.regex.Pattern;

public class MemoBirdUtils {

    public static boolean isMemoBirdIdValid(String memoBirdId) {
        return memoBirdId != null &&
                memoBirdId.length() == 16 &&
                Pattern.matches("^[0-9a-z]+$", memoBirdId);
    }

}
