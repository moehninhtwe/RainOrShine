package rainorsun.com.rainorsun;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Util {
    public static String convertMilliSecondToHourFormat(long millisecond) {
        DateFormat dateFormat = new SimpleDateFormat("hh a");
        return dateFormat.format(new Date(TimeUnit.MILLISECONDS.toHours(millisecond)));
    }
}
