package rainorsun.com.rainorsun;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import rainorsun.com.rainorsun.data.api.model.DailyWeatherData;

public class WeatherForecastDetailsFragment extends Fragment {
    public static String WEATHER_DETAILS = "weather_details";
    private TextView tvSunrise;
    private TextView tvChanceOfRain;
    private TextView tvWind;
    private TextView tvPrecipitation;
    private TextView tvVisibility;
    private TextView tvSunset;
    private TextView tvHumidity;
    private TextView tvFeelsLike;
    private TextView tvPressure;
    private TextView tvUVIndex;

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather_details, container, false);

        tvSunrise = view.findViewById(R.id.tv_sunrise);

        tvChanceOfRain = view.findViewById(R.id.tv_chance_of_rain);

        tvWind = view.findViewById(R.id.tv_wind);

        tvPrecipitation = view.findViewById(R.id.tv_precipitation);

        tvVisibility = view.findViewById(R.id.tv_visibility);

        tvSunset = view.findViewById(R.id.tv_sunset);

        tvHumidity = view.findViewById(R.id.tv_humidity);

        tvFeelsLike = view.findViewById(R.id.tv_feels_like);

        tvPressure = view.findViewById(R.id.tv_pressure);

        tvUVIndex = view.findViewById(R.id.tv_uv_index);
        if (getArguments() != null) {
            setWeatherDetails((DailyWeatherData) getArguments().getSerializable(WEATHER_DETAILS));
        }

        return view;
    }

    private void setWeatherDetails(DailyWeatherData dailyWeatherData) {
        if (dailyWeatherData != null) {
            tvSunrise.setText(
                Util.convertMilliSecondToHourMinuteFormat(dailyWeatherData.getSunriseTime()));

            tvChanceOfRain.setText(
                String.valueOf(Util.getChanceOfRain(dailyWeatherData.getPrecipProbability()))
                    + "%");

            tvWind.setText(
                "s " + String.valueOf(Math.round(dailyWeatherData.getWindSpeed())) + " km/hr");

            tvPrecipitation.setText(String.valueOf(
                Math.round(dailyWeatherData.getPrecipIntensityMax() * 1000) + " cm"));

            tvVisibility.setText(String.valueOf(dailyWeatherData.getVisibility()) + " km");

            tvSunset.setText(
                Util.convertMilliSecondToHourMinuteFormat(dailyWeatherData.getSunsetTime()));

            tvHumidity.setText(String.valueOf(dailyWeatherData.getHumidity() * 100));

            tvFeelsLike.setText(
                String.valueOf(Math.round(dailyWeatherData.getApparentTemperatureHigh()))
                    + (char) 0x00B0);

            tvPressure.setText(String.valueOf(Math.round(dailyWeatherData.getPressure())) + " hPa");

            tvUVIndex.setText(String.valueOf(dailyWeatherData.getUvIndex()));
        }
    }

    @Override public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
    }

    @Override public void onAttach(Context context) {
        super.onAttach(context);
    }
}
