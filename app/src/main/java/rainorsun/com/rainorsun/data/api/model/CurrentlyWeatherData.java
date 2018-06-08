package rainorsun.com.rainorsun.data.api.model;

public class CurrentlyWeatherData extends WeatherData {
    private int nearestStormBearing;
    private int nearestStormDistance;

    public int getNearestStormBearing() {
        return nearestStormBearing;
    }

    public void setNearestStormBearing(int nearestStormBearing) {
        this.nearestStormBearing = nearestStormBearing;
    }

    public int getNearestStormDistance() {
        return nearestStormDistance;
    }

    public void setNearestStormDistance(int nearestStormDistance) {
        this.nearestStormDistance = nearestStormDistance;
    }
}
