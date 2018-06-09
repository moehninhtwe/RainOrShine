package rainorsun.com.rainorsun.data.api.model;

/**
 * {
 * "time": 1528398000,
 * "summary": "Partly Cloudy",
 * "icon": "partly-cloudy-day",
 * "precipIntensity": 0,
 * "precipProbability": 0,
 * "temperature": 61.82,
 * "apparentTemperature": 61.82,
 * "dewPoint": 50.13,
 * "humidity": 0.66,
 * "pressure": 1016.62,
 * "windSpeed": 8.19,
 * "windGust": 11.49,
 * "windBearing": 234,
 * "cloudCover": 0.28,
 * "uvIndex": 8,
 * "visibility": 10,
 * "ozone": 336.63
 * },
 */

public class WeatherData {
    private long time;
    private String summary;
    private String icon;
    private float temperature;
    private float dewPoint;
    private float humidity;
    private float pressure;
    private float windSpeed;
    private float windGust;
    private float windBearing;
    private float cloudCover;
    private int uvIndex;
    private float visibility;
    private float ozone;
    private float precipIntensity;
    private float precipProbability;
    private String precipType;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getDewPoint() {
        return dewPoint;
    }

    public void setDewPoint(float dewPoint) {
        this.dewPoint = dewPoint;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
    }

    public float getWindGust() {
        return windGust;
    }

    public void setWindGust(float windGust) {
        this.windGust = windGust;
    }

    public float getWindBearing() {
        return windBearing;
    }

    public void setWindBearing(float windBearing) {
        this.windBearing = windBearing;
    }

    public float getCloudCover() {
        return cloudCover;
    }

    public void setCloudCover(float cloudCover) {
        this.cloudCover = cloudCover;
    }

    public int getUvIndex() {
        return uvIndex;
    }

    public void setUvIndex(int uvIndex) {
        this.uvIndex = uvIndex;
    }

    public float getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public float getOzone() {
        return ozone;
    }

    public void setOzone(float ozone) {
        this.ozone = ozone;
    }

    public float getPrecipIntensity() {
        return precipIntensity;
    }

    public void setPrecipIntensity(float precipIntensity) {
        this.precipIntensity = precipIntensity;
    }

    public float getPrecipProbability() {
        return precipProbability;
    }

    public void setPrecipProbability(float precipProbability) {
        this.precipProbability = precipProbability;
    }

    public String getPrecipType() {
        return precipType;
    }

    public void setPrecipType(String precipType) {
        this.precipType = precipType;
    }
}
