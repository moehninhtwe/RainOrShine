package rainorsun.com.rainorsun;

import android.content.Context;
import android.graphics.drawable.Drawable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Util {
    public static String convertMilliSecondToHourFormat(long millisecond) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh aa");
        date.setTime(millisecond * 1000);
        return simpleDateFormat.format(date);
    }

    public static String convertMilliSecondToHourMinuteFormat(long millisecond) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm aa");
        date.setTime(millisecond * 1000);
        return simpleDateFormat.format(date);
    }

    public static int getChanceOfRain(float chanceOfRain) {
        return (int) chanceOfRain * 100;
    }

    public static String covertMilliSecondToDay(Context context, long millisecond) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millisecond * 1000);
        return calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
    }

    public static Drawable getWeatherIcon(Context context, String icon) {
        switch (icon) {
            case Constant.CLEAR_DAY:
                return context.getResources().getDrawable(R.drawable.cloud_drizzle);
            case Constant.CLEAR_NIGHT:
                return context.getResources().getDrawable(R.drawable.cloud_drizzle);
            case Constant.PARTLY_CLOUDY:
                return context.getResources().getDrawable(R.drawable.cloud_drizzle);
            case Constant.CLOUDY:
                return context.getResources().getDrawable(R.drawable.cloud_drizzle);
            case Constant.PARTLY__CLOUDY_NIGHT:
                return context.getResources().getDrawable(R.drawable.cloud_drizzle);
            case Constant.PARTLY_CLOUDY_DAY:
                return context.getResources().getDrawable(R.drawable.cloud_drizzle);
            case Constant.FOG:
                return context.getResources().getDrawable(R.drawable.cloud_drizzle);
            case Constant.RAIN:
                return context.getResources().getDrawable(R.drawable.cloud_drizzle);
            case Constant.SLEET:
                return context.getResources().getDrawable(R.drawable.cloud_drizzle);
            case Constant.SNOW:
                return context.getResources().getDrawable(R.drawable.cloud_drizzle);
            case Constant.WIND:
                return context.getResources().getDrawable(R.drawable.cloud_drizzle);
        }
        return null;
    }
}
