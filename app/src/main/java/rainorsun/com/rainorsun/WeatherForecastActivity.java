package rainorsun.com.rainorsun;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import rainorsun.com.rainorsun.adapter.HourlyWeatherAdapter;
import rainorsun.com.rainorsun.data.api.model.DarkSkyResponse;
import rainorsun.com.rainorsun.data.api.model.Hourly;
import rainorsun.com.rainorsun.data.api.provider.GetWeatherForecast;

public class WeatherForecastActivity extends AppCompatActivity {
    RecyclerView rvHourlyData;
    HourlyWeatherAdapter hourlyWeatherAdapter;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);
        rvHourlyData = findViewById(R.id.rv_hourly_forecast);
        rvHourlyData.setLayoutManager(
            new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        hourlyWeatherAdapter = new HourlyWeatherAdapter();
    }

    @Override protected void onResume() {
        super.onResume();
        GetWeatherForecast.getWeatherForecast(new GetWeatherForecast.GetWeatherForecastCallBack() {
            @Override public void onSuccess(DarkSkyResponse darkSkyResponse) {
                updateHourlyWeatherUI(darkSkyResponse.getHourly());
            }

            @Override public void onFailure(String errorMsg) {
            }
        });

    }

    private void updateHourlyWeatherUI(Hourly hourly) {
        if(hourly.getData() != null) {
            hourlyWeatherAdapter.setListOfHourlyData(hourly.getData());
        }
    }
}
