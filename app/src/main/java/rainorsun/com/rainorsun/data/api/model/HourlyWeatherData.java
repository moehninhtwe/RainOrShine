package rainorsun.com.rainorsun.data.api.model;

public class HourlyWeatherData extends WeatherData {
    private float apparentTemperature;
    private float precipAccumulation;

    public float getApparentTemperature() {
        return apparentTemperature;
    }

    public void setApparentTemperature(float apparentTemperature) {
        this.apparentTemperature = apparentTemperature;
    }

    public float getPrecipAccumulation() {
        return precipAccumulation;
    }

    public void setPrecipAccumulation(float precipAccumulation) {
        this.precipAccumulation = precipAccumulation;
    }
}
