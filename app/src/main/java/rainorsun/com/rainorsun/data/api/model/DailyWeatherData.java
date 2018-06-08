package rainorsun.com.rainorsun.data.api.model;

public class DailyWeatherData extends WeatherData {
    private float apparentTemperatureHigh;
    private float apparentTemperatureHighTime;
    private float apparentTemperatureLow;
    private float apparentTemperatureLowTime;
    private float moonPhase;
    private float precipAccumulation;
    private float precipIntensityMax;
    private float precipIntensityMaxTime;
    private long sunriseTime;
    private long sunsetTime;
    private float temperatureHigh;
    private float temperatureHighTime;
    private float temperatureLow;
    private float temperatureLowTime;
    private long uvIndexTime;

    public float getApparentTemperatureHigh() {
        return apparentTemperatureHigh;
    }

    public void setApparentTemperatureHigh(float apparentTemperatureHigh) {
        this.apparentTemperatureHigh = apparentTemperatureHigh;
    }

    public float getApparentTemperatureHighTime() {
        return apparentTemperatureHighTime;
    }

    public void setApparentTemperatureHighTime(float apparentTemperatureHighTime) {
        this.apparentTemperatureHighTime = apparentTemperatureHighTime;
    }

    public float getApparentTemperatureLow() {
        return apparentTemperatureLow;
    }

    public void setApparentTemperatureLow(float apparentTemperatureLow) {
        this.apparentTemperatureLow = apparentTemperatureLow;
    }

    public float getApparentTemperatureLowTime() {
        return apparentTemperatureLowTime;
    }

    public void setApparentTemperatureLowTime(float apparentTemperatureLowTime) {
        this.apparentTemperatureLowTime = apparentTemperatureLowTime;
    }

    public float getMoonPhase() {
        return moonPhase;
    }

    public void setMoonPhase(float moonPhase) {
        this.moonPhase = moonPhase;
    }

    public float getPrecipAccumulation() {
        return precipAccumulation;
    }

    public void setPrecipAccumulation(float precipAccumulation) {
        this.precipAccumulation = precipAccumulation;
    }

    public float getPrecipIntensityMax() {
        return precipIntensityMax;
    }

    public void setPrecipIntensityMax(float precipIntensityMax) {
        this.precipIntensityMax = precipIntensityMax;
    }

    public float getPrecipIntensityMaxTime() {
        return precipIntensityMaxTime;
    }

    public void setPrecipIntensityMaxTime(float precipIntensityMaxTime) {
        this.precipIntensityMaxTime = precipIntensityMaxTime;
    }

    public long getSunriseTime() {
        return sunriseTime;
    }

    public void setSunriseTime(long sunriseTime) {
        this.sunriseTime = sunriseTime;
    }

    public long getSunsetTime() {
        return sunsetTime;
    }

    public void setSunsetTime(long sunsetTime) {
        this.sunsetTime = sunsetTime;
    }

    public float getTemperatureHigh() {
        return temperatureHigh;
    }

    public void setTemperatureHigh(float temperatureHigh) {
        this.temperatureHigh = temperatureHigh;
    }

    public float getTemperatureHighTime() {
        return temperatureHighTime;
    }

    public void setTemperatureHighTime(float temperatureHighTime) {
        this.temperatureHighTime = temperatureHighTime;
    }

    public float getTemperatureLow() {
        return temperatureLow;
    }

    public void setTemperatureLow(float temperatureLow) {
        this.temperatureLow = temperatureLow;
    }

    public float getTemperatureLowTime() {
        return temperatureLowTime;
    }

    public void setTemperatureLowTime(float temperatureLowTime) {
        this.temperatureLowTime = temperatureLowTime;
    }

    public long getUvIndexTime() {
        return uvIndexTime;
    }

    public void setUvIndexTime(long uvIndexTime) {
        this.uvIndexTime = uvIndexTime;
    }
}
