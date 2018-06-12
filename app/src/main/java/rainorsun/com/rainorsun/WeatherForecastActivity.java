package rainorsun.com.rainorsun;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import java.util.concurrent.TimeUnit;
import rainorsun.com.rainorsun.adapter.DailyWeatherAdapter;
import rainorsun.com.rainorsun.adapter.HourlyWeatherAdapter;
import rainorsun.com.rainorsun.data.api.model.CurrentlyWeatherData;
import rainorsun.com.rainorsun.data.api.model.Daily;
import rainorsun.com.rainorsun.data.api.model.DarkSkyResponse;
import rainorsun.com.rainorsun.data.api.model.Hourly;
import rainorsun.com.rainorsun.data.api.provider.GetWeatherForecast;

public class WeatherForecastActivity extends AppCompatActivity {
    private RecyclerView rvHourlyData;
    private RecyclerView rvDailyData;
    private HourlyWeatherAdapter hourlyWeatherAdapter;
    private DailyWeatherAdapter dailyWeatherAdapter;
    private TextView tvLocation;
    private TextView tvCondition;
    private TextView tvTemperature;
    private TextView tvDay;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);

        /** set adapter for hourly view **/
        rvHourlyData = findViewById(R.id.rv_hourly_forecast);
        rvHourlyData.setLayoutManager(
            new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        hourlyWeatherAdapter = new HourlyWeatherAdapter(this);
        rvHourlyData.setAdapter(hourlyWeatherAdapter);

        /** set adapter for daily view **/
        rvDailyData = findViewById(R.id.rv_daily_forecast);
        rvDailyData.setLayoutManager(
            new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        dailyWeatherAdapter = new DailyWeatherAdapter(this);
        rvDailyData.setAdapter(dailyWeatherAdapter);

        tvLocation = findViewById(R.id.tv_location);
        tvCondition = findViewById(R.id.tv_condition);
        tvTemperature = findViewById(R.id.tv_temperature);
        tvDay = findViewById(R.id.tv_day);
    }

    @Override protected void onResume() {
        super.onResume();
        GetWeatherForecast.getWeatherForecast(new GetWeatherForecast.GetWeatherForecastCallBack() {
            @Override public void onSuccess(DarkSkyResponse darkSkyResponse) {
                setUI(darkSkyResponse);
            }

            @Override public void onFailure(String errorMsg) {
            }
        });
    }

    private void setUI(DarkSkyResponse darkSkyResponse) {
        setHourlyWeatherUI(darkSkyResponse.getHourly());
        setDailyWeatherUI(darkSkyResponse.getDaily());
        setTodayWeatherUI(darkSkyResponse.getCurrently());
    }

    private void setTodayWeatherUI(CurrentlyWeatherData currentlyWeatherData) {
        tvCondition.setText(currentlyWeatherData.getSummary());
        tvTemperature.setText(String.valueOf(currentlyWeatherData.getTemperature()));
        tvDay.setText(Util.covertMilliSecondToDay(this, currentlyWeatherData.getTime()));
    }

    private void setHourlyWeatherUI(Hourly hourlyData) {
        if (hourlyData.getData() != null) {
            hourlyWeatherAdapter.setListOfHourlyData(hourlyData.getData());
        }
    }

    private void setDailyWeatherUI(Daily dailyData) {
        if (dailyData.getData() != null) {
            dailyWeatherAdapter.setDailyWeatherData(dailyData.getData());
        }
    }
}
