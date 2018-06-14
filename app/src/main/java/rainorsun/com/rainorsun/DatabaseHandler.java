package rainorsun.com.rainorsun;

import android.content.Context;
import android.os.AsyncTask;
import java.util.List;
import rainorsun.com.rainorsun.sqliteDatabase.WeatherForecaseDBHelper;
import rainorsun.com.rainorsun.sqliteDatabase.model.VisitedLocation;

public class DatabaseHandler {
    private WeatherForecaseDBHelper weatherForecaseDBHelper;

    public DatabaseHandler(Context context) {
        weatherForecaseDBHelper = new WeatherForecaseDBHelper(context);
    }

    public void saveVisitedLocation(VisitedLocation visitedLocation) {
        new SaveVisitedLocationAsyncTask(visitedLocation, weatherForecaseDBHelper).execute();
    }

    public void removeVisitedLocation(VisitedLocation visitedLocation,
        RemoveVisitedLocationsListener removeVisitedLocationsListener) {
        new RemoveVisitedLocationAsyncTask(visitedLocation, weatherForecaseDBHelper,
            removeVisitedLocationsListener).execute();
    }

    public void getVisitedLocations(GetVisitedLocationsListener getVisitedLocationsListener) {
        new GetVisitedLocationsAsyncTask(getVisitedLocationsListener,
            weatherForecaseDBHelper).execute();
    }

    private static class RemoveVisitedLocationAsyncTask extends AsyncTask<Void, Void, Void> {
        private VisitedLocation visitedLocation;
        private WeatherForecaseDBHelper weatherForecaseDBHelper;
        private RemoveVisitedLocationsListener removeVisitedLocationsListener;

        public RemoveVisitedLocationAsyncTask(VisitedLocation visitedLocation,
            WeatherForecaseDBHelper helper,
            RemoveVisitedLocationsListener removeVisitedLocationsListener) {
            this.visitedLocation = visitedLocation;
            this.weatherForecaseDBHelper = helper;
            this.removeVisitedLocationsListener = removeVisitedLocationsListener;
        }

        @Override protected Void doInBackground(Void... params) {
            weatherForecaseDBHelper.deleteVisitedLocation(visitedLocation);
            return null;
        }

        @Override protected void onPostExecute(Void params) {
            removeVisitedLocationsListener.onLocationsRemoved();
        }
    }

    private static class SaveVisitedLocationAsyncTask extends AsyncTask<Void, Void, Void> {
        private VisitedLocation visitedLocation;
        private WeatherForecaseDBHelper weatherForecaseDBHelper;

        public SaveVisitedLocationAsyncTask(VisitedLocation visitedLocation,
            WeatherForecaseDBHelper helper) {
            this.visitedLocation = visitedLocation;
            this.weatherForecaseDBHelper = helper;
        }

        @Override protected Void doInBackground(Void... voids) {
            weatherForecaseDBHelper.insertVisitedLocation(visitedLocation);
            return null;
        }
    }

    private static class GetVisitedLocationsAsyncTask
        extends AsyncTask<Void, Void, List<VisitedLocation>> {
        private GetVisitedLocationsListener getVisitedLocationsListener;
        private WeatherForecaseDBHelper weatherForecaseDBHelper;

        public GetVisitedLocationsAsyncTask(GetVisitedLocationsListener getVisitedLocationsListener,
            WeatherForecaseDBHelper weatherForecaseDBHelper) {
            this.getVisitedLocationsListener = getVisitedLocationsListener;
            this.weatherForecaseDBHelper = weatherForecaseDBHelper;
        }

        @Override protected List<VisitedLocation> doInBackground(Void... params) {
            return weatherForecaseDBHelper.getVisitedLocation();
        }

        @Override protected void onPostExecute(List<VisitedLocation> visitedLocations) {
            getVisitedLocationsListener.onLocationsFetched(visitedLocations);
        }
    }

    public interface GetVisitedLocationsListener {
        void onLocationsFetched(List<VisitedLocation> visitedLocations);
    }

    public interface RemoveVisitedLocationsListener {
        void onLocationsRemoved();
    }
}
