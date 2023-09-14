package librarysystem.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtil {

    private static final String DATE_PATTERN = "MM/dd/yyyy";

    private DateUtil() {
    }

    public static String format(Date date) {
        return getDateFormat().format(date);
    }

    public static Date parse(String date) throws ParseException {
        return getDateFormat().parse(date);
    }

    private static SimpleDateFormat getDateFormat() {
        return new SimpleDateFormat(DATE_PATTERN);
    }

}
