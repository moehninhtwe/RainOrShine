package rainorsun.com.rainorsun.data.api.model;

public class DarkSkyResponse {
    private CurrentlyWeatherData currently;
    private HourlyWeatherData[] hourly;
    private DailyWeatherData[] daily;

    public CurrentlyWeatherData getCurrently() {
        return currently;
    }

    public void setCurrently(CurrentlyWeatherData currently) {
        this.currently = currently;
    }

    public HourlyWeatherData[] getHourly() {
        return hourly;
    }

    public void setHourly(HourlyWeatherData[] hourly) {
        this.hourly = hourly;
    }

    public DailyWeatherData[] getDaily() {
        return daily;
    }

    public void setDaily(DailyWeatherData[] daily) {
        this.daily = daily;
    }
}
