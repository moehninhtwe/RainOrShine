package rainorsun.com.rainorsun.ui;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import rainorsun.com.rainorsun.R;
import rainorsun.com.rainorsun.ui.adapter.DailyWeatherAdapter;
import rainorsun.com.rainorsun.ui.adapter.HourlyWeatherAdapter;
import rainorsun.com.rainorsun.data.api.model.CurrentlyWeatherData;
import rainorsun.com.rainorsun.data.api.model.Daily;
import rainorsun.com.rainorsun.data.api.model.DailyWeatherData;
import rainorsun.com.rainorsun.data.api.model.DarkSkyResponse;
import rainorsun.com.rainorsun.data.api.model.Hourly;
import rainorsun.com.rainorsun.data.api.provider.GetWeatherForecast;
import rainorsun.com.rainorsun.sqliteDatabase.model.VisitedLocation;
import utils.DatabaseHandler;
import utils.LocationHandler;
import utils.Util;

public class WeatherForecastActivity extends AppCompatActivity
    implements DailyWeatherAdapter.OnWeatherForecastClickListener {
    private RecyclerView rvHourlyData;
    private RecyclerView rvDailyData;
    private HourlyWeatherAdapter hourlyWeatherAdapter;
    private DailyWeatherAdapter dailyWeatherAdapter;
    private TextView tvLocation;
    private TextView tvTodayCondition;
    private TextView tvTodayTemperature;
    private TextView tvDayOfToday;
    private TextView tvTodayHighTemperature;
    private TextView tvTodayLowTemperature;
    private TextView tvNoWeatherData;
    private ConstraintLayout clWeatherData;
    private WeatherForecastDetailsFragment weatherForecastDetailsFragment;
    private LocationHandler locationHandler;
    public static String LOCATION = "location";
    private String coordinate;
    private String locationName;
    private ProgressBar progressBar;

    @Override public void onWeatherForecastClick(View view, DailyWeatherData dailyWeatherData) {
        Intent intent = new Intent(this, WeatherDetailsActivity.class);
        intent.putExtra(WeatherForecastDetailsFragment.WEATHER_DETAILS, dailyWeatherData);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);

        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            VisitedLocation visitedLocation = (VisitedLocation) bundle.getSerializable(LOCATION);
            if (visitedLocation != null) {
                coordinate = visitedLocation.getCoordinate();
                locationName = visitedLocation.getAddress();
            }
        }
        /** set adapter for hourly view **/
        rvHourlyData = findViewById(R.id.rv_hourly_forecast);
        rvHourlyData.setLayoutManager(
            new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        hourlyWeatherAdapter = new HourlyWeatherAdapter(this);

        /** set adapter for daily view **/
        rvDailyData = findViewById(R.id.rv_daily_forecast);
        rvDailyData.setLayoutManager(
            new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        dailyWeatherAdapter = new DailyWeatherAdapter(this, this::onWeatherForecastClick);

        /** for currently view **/
        tvLocation = findViewById(R.id.tv_location);
        tvTodayCondition = findViewById(R.id.tv_condition);
        tvTodayTemperature = findViewById(R.id.tv_temperature);
        tvDayOfToday = findViewById(R.id.tv_day);
        tvTodayHighTemperature = findViewById(R.id.tv_high_temperature);
        tvTodayLowTemperature = findViewById(R.id.tv_low_temperature);

        progressBar = findViewById(R.id.pb_weather_data);
        tvNoWeatherData = findViewById(R.id.tv_no_data);
        clWeatherData = findViewById(R.id.cl_weather_data);

        weatherForecastDetailsFragment = new WeatherForecastDetailsFragment();
        locationHandler = new LocationHandler();
    }

    @Override protected void onNewIntent(Intent intent) {
        if (intent != null) {
            setIntent(intent);
        }
    }

    @Override protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.VISIBLE);
        clWeatherData.setVisibility(View.GONE);
        tvNoWeatherData.setVisibility(View.GONE);
        if (locationName != null && coordinate != null) {
            tvLocation.setText(locationName);
            getWeatherForecast(coordinate);
        } else {
            getCurrentLocation();
        }
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_more:
                Intent intent = new Intent(this, WeatherForecastForVisitedLocationActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    private void getCurrentLocation() {
        locationHandler.requestLocation(this, new LocationHandler.LocationHandlerListener() {
            @Override public void onLocationReceived(Location location) {
                String strLocation = location.getLatitude() + "," + location.getLongitude();
                getWeatherForecast(strLocation);
                getCurrentAddress(location);
            }

            @Override public void onLocationCancelled() {
                setEmptyUI();
            }
        });
    }

    private void getWeatherForecast(String location) {
        GetWeatherForecast.getWeatherForecast(location,
            new GetWeatherForecast.GetWeatherForecastCallBack() {
                @Override public void onSuccess(DarkSkyResponse darkSkyResponse) {
                    if (darkSkyResponse != null) {
                        setUI(darkSkyResponse);
                    } else {
                        setEmptyUI();
                    }
                }

                @Override public void onFailure(String errorMsg) {
                    Log.d("Rain or sun", errorMsg);
                }
            });
    }

    private void getCurrentAddress(Location location) {
        locationHandler.getCompleteAddressString(this, location.getLatitude(),
            location.getLongitude(), address -> {
                VisitedLocation visitedLocation = new VisitedLocation(address,
                    location.getLatitude() + "," + location.getLongitude());
                tvLocation.setText(address);
                saveVisitedLocation(visitedLocation);
            });
    }

    private void saveVisitedLocation(VisitedLocation visitedLocation) {
        new DatabaseHandler(this).saveVisitedLocation(visitedLocation);
    }

    private void setEmptyUI() {
        progressBar.setVisibility(View.GONE);
        tvNoWeatherData.setVisibility(View.VISIBLE);
        clWeatherData.setVisibility(View.GONE);
    }

    private void setUI(DarkSkyResponse darkSkyResponse) {
        progressBar.setVisibility(View.GONE);
        tvNoWeatherData.setVisibility(View.GONE);
        clWeatherData.setVisibility(View.VISIBLE);
        setCurrentlyWeatherUI(darkSkyResponse.getCurrently());
        setHourlyWeatherUI(darkSkyResponse.getHourly());
        setDailyWeatherUI(darkSkyResponse.getDaily());
        setTodayWeatherUI(darkSkyResponse.getDaily().getData()[0]);
    }

    private void setTodayWeatherUI(DailyWeatherData dailyWeatherData) {
        tvDayOfToday.setText(Util.covertMilliSecondToDay(dailyWeatherData.getTime()));
        tvTodayHighTemperature.setText(
            String.valueOf(Math.round(dailyWeatherData.getApparentTemperatureHigh())));
        tvTodayLowTemperature.setText(
            String.valueOf(Math.round(dailyWeatherData.getApparentTemperatureLow())));
        if (weatherForecastDetailsFragment != null && !weatherForecastDetailsFragment.isAdded()) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(WeatherForecastDetailsFragment.WEATHER_DETAILS,
                dailyWeatherData);
            weatherForecastDetailsFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                .add(R.id.ll_today_weather_details, weatherForecastDetailsFragment)
                .commit();
        }
    }

    private void setCurrentlyWeatherUI(CurrentlyWeatherData currentlyWeatherdata) {
        tvTodayCondition.setText(currentlyWeatherdata.getIcon().replace("-", " "));
        tvTodayTemperature.setText(
            String.valueOf(Math.round(currentlyWeatherdata.getTemperature())) + (char) 0x00B0);
    }

    private void setHourlyWeatherUI(Hourly hourlyData) {
        if (hourlyData.getData() != null) {
            hourlyWeatherAdapter.setListOfHourlyData(hourlyData.getData());
        }
        rvHourlyData.setAdapter(hourlyWeatherAdapter);
    }

    private void setDailyWeatherUI(Daily dailyData) {
        if (dailyData.getData() != null) {
            List<DailyWeatherData> listOfDailyWeatherData =
                new ArrayList<>(Arrays.asList(dailyData.getData()));
            listOfDailyWeatherData.remove(0);
            dailyWeatherAdapter.setDailyWeatherData(listOfDailyWeatherData);
        }
        rvDailyData.setAdapter(dailyWeatherAdapter);
    }
}
