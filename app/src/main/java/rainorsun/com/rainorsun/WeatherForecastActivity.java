package rainorsun.com.rainorsun;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import rainorsun.com.rainorsun.data.api.provider.GetWeatherForecast;

public class WeatherForecastActivity extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);
        GetWeatherForecast.getWeatherForecast();
    }
}
