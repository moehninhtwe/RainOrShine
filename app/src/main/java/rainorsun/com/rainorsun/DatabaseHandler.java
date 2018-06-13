package rainorsun.com.rainorsun;

import android.content.Context;
import android.os.AsyncTask;
import rainorsun.com.rainorsun.sqliteDatabase.WeatherForecaseDBHelper;
import rainorsun.com.rainorsun.sqliteDatabase.model.VisitedLocation;

public class DatabaseHandler {
    private WeatherForecaseDBHelper weatherForecaseDBHelper;

    public DatabaseHandler(Context context) {
        weatherForecaseDBHelper = new WeatherForecaseDBHelper(context);
    }

    public void saveVisitedLocation(VisitedLocation visitedLocation) {
        new SaveVisitedLocationAsyncTask(visitedLocation, weatherForecaseDBHelper);
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
}
