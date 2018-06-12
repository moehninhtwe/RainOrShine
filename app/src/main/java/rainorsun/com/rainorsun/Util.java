package rainorsun.com.rainorsun;

import android.content.Context;
import android.graphics.drawable.Drawable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Util {
    public static String convertMilliSecondToHourFormat(long millisecond) {
        DateFormat dateFormat = new SimpleDateFormat("hh a");
        return dateFormat.format(new Date(TimeUnit.MILLISECONDS.toHours(millisecond)));
    }

    public static String covertMilliSecondToDay(Context context, long millisecond) {
        int day = (int) TimeUnit.MILLISECONDS.toDays(millisecond);
        switch (day) {
            case Calendar.MONDAY:
                return context.getString(R.string.monday);
            case Calendar.TUESDAY:
                return context.getString(R.string.tuesday);
            case Calendar.WEDNESDAY:
                return context.getString(R.string.wednesday);
            case Calendar.THURSDAY:
                return context.getString(R.string.thursday);
            case Calendar.FRIDAY:
                return context.getString(R.string.friday);
            case Calendar.SATURDAY:
                return context.getString(R.string.saturday);
            case Calendar.SUNDAY:
                return context.getString(R.string.sunday);
        }
        return null;
    }

    public static Drawable getWeatherIcon(Context context, String icon) {
        switch (icon) {
            case Constant.CLEAR_DAY:
                return context.getResources().getDrawable(R.drawable.ic_launcher_background);
            case Constant.CLEAR_NIGHT:
                return context.getResources().getDrawable(R.drawable.ic_launcher_background);
            case Constant.PARTLY_CLOUDY:
                return context.getResources().getDrawable(R.drawable.ic_launcher_background);
            case Constant.CLOUDY:
                return context.getResources().getDrawable(R.drawable.ic_launcher_background);
            case Constant.PARTLY__CLOUDY_NIGHT:
                return context.getResources().getDrawable(R.drawable.ic_launcher_background);
            case Constant.FOG:
                return context.getResources().getDrawable(R.drawable.ic_launcher_background);
            case Constant.RAIN:
                return context.getResources().getDrawable(R.drawable.ic_launcher_background);
            case Constant.SLEET:
                return context.getResources().getDrawable(R.drawable.ic_launcher_background);
            case Constant.SNOW:
                return context.getResources().getDrawable(R.drawable.ic_launcher_background);
            case Constant.WIND:
                return context.getResources().getDrawable(R.drawable.ic_launcher_background);
        }
        return null;
    }
}
