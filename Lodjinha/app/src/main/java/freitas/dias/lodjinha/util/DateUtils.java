package freitas.dias.lodjinha.util;


import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    private static final String LOG = DateUtils.class.getSimpleName();

    public static final String SIMPLE_DATE_FORMAT = "yyyy-MM-dd";
    public static final Locale BR_LOCALE = new Locale("pt", "BR");

    private DateUtils() {
    }


    public static Date formatDate(final String date) {
        return formatDate(SIMPLE_DATE_FORMAT, date);
    }

    public static Date formatDate(final String formatType, final String date) {
        if (!StringUtils.notEmpty(date) || formatType == null) {
            return null;
        }

        try {
            final SimpleDateFormat dateFormatterInstance = new SimpleDateFormat(formatType, BR_LOCALE);
            return dateFormatterInstance.parse(date);
        } catch (ParseException e) {
            Log.e(LOG, e.getMessage());
            return null;
        }
    }

    public static String formatDate(final String formatType, final Date date) {
        if (date == null || formatType == null) {
            return null;
        }

        final SimpleDateFormat dateFormatterInstance = new SimpleDateFormat(formatType, BR_LOCALE);
        return dateFormatterInstance.format(date);
    }
}
