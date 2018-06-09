package rainorsun.com.rainorsun.data.api.service;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import rainorsun.com.rainorsun.BuildConfig;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIService {
    private static Interceptor darkSkyRequestInterceptor = (Interceptor.Chain chain) -> {
        Request request = chain.request();
        request = request.newBuilder().build();
        return chain.proceed(request);
    };
    private static HttpUrl endpoint = HttpUrl.parse("https://api.darksky.net/");

    public static Retrofit provideAdapter() {
        okhttp3.OkHttpClient client = new OkHttpClient();
        final OkHttpClient.Builder builder =
            client.newBuilder().addInterceptor(darkSkyRequestInterceptor);
        if (BuildConfig.DEBUG) {
            final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }
        final Retrofit.Builder restAdapterBuilder = new Retrofit.Builder().client(builder.build())
            .baseUrl(endpoint)
            .addConverterFactory(GsonConverterFactory.create());
        return restAdapterBuilder.build();
    }

    public static WeatherForecastService provideWeatherForecastService() {
        return provideAdapter().create(WeatherForecastService.class);
    }
}
