package rainorsun.com.rainorsun.data.api.provider;

import android.location.Location;
import rainorsun.com.rainorsun.data.api.model.DarkSkyResponse;
import rainorsun.com.rainorsun.data.api.service.APIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetWeatherForecast {
    public static void getWeatherForecast(Location location,
        GetWeatherForecastCallBack getWeatherForecastCallback) {
        String apiKey = "9b273fb4c660e52365e751d0f99f06c0";
        String strLocation = location.getLatitude() + "," + location.getLongitude();
        Call<DarkSkyResponse> response =
            APIService.provideWeatherForecastService().getWeatherForecast(apiKey, strLocation);
        response.enqueue(new Callback<DarkSkyResponse>() {
            @Override
            public void onResponse(Call<DarkSkyResponse> call, Response<DarkSkyResponse> response) {
                getWeatherForecastCallback.onSuccess(response.body());
            }

            @Override public void onFailure(Call<DarkSkyResponse> call, Throwable throwable) {
                getWeatherForecastCallback.onFailure(throwable.getMessage());
            }
        });
    }

    public interface GetWeatherForecastCallBack {
        void onSuccess(DarkSkyResponse darkSkyResponse);

        void onFailure(String errorMsg);
    }
}
