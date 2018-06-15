package utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import rainorsun.com.rainorsun.Constant;
import rainorsun.com.rainorsun.R;

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

    public static String covertMilliSecondToDay(long millisecond) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millisecond * 1000);
        return calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
    }

    public static Drawable getWeatherIcon(Context context, String icon) {
        switch (icon) {
            case Constant.CLEAR_DAY:
                return context.getResources().getDrawable(R.drawable.ic_clear_day);
            case Constant.CLEAR_NIGHT:
                return context.getResources().getDrawable(R.drawable.ic_clear_night);
            case Constant.PARTLY_CLOUDY:
                return context.getResources().getDrawable(R.drawable.ic_cloud);
            case Constant.CLOUDY:
                return context.getResources().getDrawable(R.drawable.ic_cloud);
            case Constant.PARTLY__CLOUDY_NIGHT:
                return context.getResources().getDrawable(R.drawable.ic_partly_cloudy_night);
            case Constant.PARTLY_CLOUDY_DAY:
                return context.getResources().getDrawable(R.drawable.ic_partly_cloudy_day);
            case Constant.FOG:
                return context.getResources().getDrawable(R.drawable.ic_fog);
            case Constant.RAIN:
                return context.getResources().getDrawable(R.drawable.ic_cloud_rain);
            case Constant.SLEET:
                return context.getResources().getDrawable(R.drawable.ic_cloud_rain);
            case Constant.SNOW:
                return context.getResources().getDrawable(R.drawable.ic_snowflake);
            case Constant.WIND:
                return context.getResources().getDrawable(R.drawable.ic_wind);
        }
        return null;
    }
}
