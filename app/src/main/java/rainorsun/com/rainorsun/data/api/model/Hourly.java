package rainorsun.com.rainorsun.data.api.model;

public class Hourly {
    private String summary;
    private String icon;
    private HourlyWeatherData[] data;

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

    public HourlyWeatherData[] getData() {
        return data;
    }

    public void setData(HourlyWeatherData[] data) {
        this.data = data;
    }
}
