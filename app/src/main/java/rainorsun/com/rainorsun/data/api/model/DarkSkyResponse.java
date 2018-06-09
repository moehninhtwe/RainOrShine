package rainorsun.com.rainorsun.data.api.model;

public class DarkSkyResponse {
    private CurrentlyWeatherData currently;
    private Hourly hourly;
    private Daily daily;

    public CurrentlyWeatherData getCurrently() {
        return currently;
    }

    public void setCurrently(CurrentlyWeatherData currently) {
        this.currently = currently;
    }

    public Hourly getHourly() {
        return hourly;
    }

    public void setHourly(Hourly hourly) {
        this.hourly = hourly;
    }

    public Daily getDaily() {
        return daily;
    }

    public void setDaily(Daily daily) {
        this.daily = daily;
    }
}
