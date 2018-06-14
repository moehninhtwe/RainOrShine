package rainorsun.com.rainorsun;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class WeatherDetailsActivity extends AppCompatActivity {
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_details);
        WeatherForecastDetailsFragment fragment = new WeatherForecastDetailsFragment();
        if (fragment != null && !fragment.isAdded()) {
            Bundle bundle = getIntent().getExtras();
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                .add(R.id.ll_weather_details, fragment)
                .commit();
        }
    }
}
