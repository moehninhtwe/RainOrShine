package rainorsun.com.rainorsun.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import rainorsun.com.rainorsun.R;
import rainorsun.com.rainorsun.data.api.model.DailyWeatherData;
import utils.Util;

public class WeatherDetailsActivity extends AppCompatActivity {
    private TextView tvDay;
    private TextView tvHighTemperature;
    private TextView tvLowTemperature;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_details);
        tvDay = findViewById(R.id.tv_day);
        tvHighTemperature = findViewById(R.id.tv_high_temperature);
        tvLowTemperature = findViewById(R.id.tv_low_temperature);
        if (getIntent().getExtras() != null) {
            WeatherForecastDetailsFragment fragment = new WeatherForecastDetailsFragment();
            Bundle bundle = getIntent().getExtras();
            setUI((DailyWeatherData) bundle.getSerializable(
                WeatherForecastDetailsFragment.WEATHER_DETAILS));
            fragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                .add(R.id.ll_weather_details, fragment)
                .commit();
        }
    }

    private void setUI(DailyWeatherData dailyWeatherData) {
        tvDay.setText(Util.covertMilliSecondToDay(dailyWeatherData.getTime()));
        tvHighTemperature.setText(
            String.valueOf(Math.round(dailyWeatherData.getApparentTemperatureHigh())));
        tvLowTemperature.setText(
            String.valueOf(Math.round(dailyWeatherData.getApparentTemperatureLow())));
    }
}
