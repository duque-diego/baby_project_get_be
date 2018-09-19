package app.getfraldas.utils;

import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by fprado on 19/09/18
 */

@Component
public class DateUtils {

    public static Calendar convertCalendar(final Calendar calendar, final TimeZone timeZone) {
        Calendar ret = new GregorianCalendar(timeZone);
        ret.setTimeInMillis(calendar.getTimeInMillis() +
                timeZone.getOffset(calendar.getTimeInMillis()) -
                TimeZone.getDefault().getOffset(calendar.getTimeInMillis()));
        ret.getTime();
        return ret;
    }

    public static Date corrigeTimezone(Date date, String timeZone) {
        Calendar inputCalendar = Calendar.getInstance();
        inputCalendar.setTime(date);
        Calendar calendar = convertCalendar(inputCalendar, TimeZone.getTimeZone(timeZone));
        TimeZone tz = TimeZone.getTimeZone(timeZone);
        calendar.setTimeZone(tz);
        return calendar.getTime();
    }

}
