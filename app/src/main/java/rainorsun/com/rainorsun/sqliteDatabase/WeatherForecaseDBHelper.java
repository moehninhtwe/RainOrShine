package rainorsun.com.rainorsun.sqliteDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;
import rainorsun.com.rainorsun.sqliteDatabase.model.VisitedLocation;

public class WeatherForecaseDBHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "WeatherForecast.db";

    public WeatherForecaseDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override public void onCreate(SQLiteDatabase db) {
        db.execSQL(VisitedLocation.SQL_CREATE_ENTRIES);
    }

    @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(VisitedLocation.SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void insertVisitedLocation(VisitedLocation visitedLocation) {
        if (!isAlreadySaved(visitedLocation)) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(VisitedLocation.COLUMN_NAME_LOCATION_ADDRESS, visitedLocation.getAddress());
            values.put(VisitedLocation.COLUMN_NAME_LOCATION_COORDINATES,
                visitedLocation.getCoordinate());
            db.insert(VisitedLocation.TABLE_NAME, null, values);
            db.close();
        }
    }

    private boolean isAlreadySaved(VisitedLocation visitedLocation) {
        boolean isAlreadySaved = false;
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + VisitedLocation.TABLE_NAME + " WHERE "
            + VisitedLocation.COLUMN_NAME_LOCATION_ADDRESS + "='" + visitedLocation.getAddress()
            + "'";
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) isAlreadySaved = true;
        return isAlreadySaved;
    }

    public List<VisitedLocation> getVisitedLocation() {
        List<VisitedLocation> listOfLocations = new ArrayList<>();
        String address, coordinate;
        VisitedLocation visitedLocation;
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + VisitedLocation.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                address = cursor.getString(
                    cursor.getColumnIndex(VisitedLocation.COLUMN_NAME_LOCATION_ADDRESS));
                coordinate = cursor.getString(
                    cursor.getColumnIndex(VisitedLocation.COLUMN_NAME_LOCATION_COORDINATES));
                visitedLocation = new VisitedLocation(address, coordinate);
                listOfLocations.add(visitedLocation);
            } while (cursor.moveToNext());
        }
        db.close();
        return listOfLocations;
    }

    public void deleteVisitedLocation(VisitedLocation visitedLocation) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(VisitedLocation.TABLE_NAME, VisitedLocation.COLUMN_NAME_LOCATION_ADDRESS + " = ?",
            new String[] { String.valueOf(visitedLocation.getAddress()) });
        db.close();
    }
}

