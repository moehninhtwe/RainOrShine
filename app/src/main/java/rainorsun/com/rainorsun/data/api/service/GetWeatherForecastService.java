package rainorsun.com.rainorsun.data.api.service;

import rainorsun.com.rainorsun.data.api.model.DarkSkyResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetWeatherForecastService {
    @GET("/forecast/{apikey}/{location}") Call<DarkSkyResponse> getWeatherForecast(
        @Path("apikey") String apiKey, @Path("location") String location);
}
