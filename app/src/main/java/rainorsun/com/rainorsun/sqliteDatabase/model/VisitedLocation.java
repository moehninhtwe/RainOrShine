package rainorsun.com.rainorsun.sqliteDatabase.model;

public class VisitedLocation {
    public static final String TABLE_NAME = "visitedLocation";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME_LOCATION_ADDRESS = "address";
    public static final String COLUMN_NAME_LOCATION_COORDINATES = "coordinates";

    private String address;
    private String coordinate;

    public static final String SQL_CREATE_ENTRIES =
        "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NAME_LOCATION_ADDRESS + " TEXT," + COLUMN_NAME_LOCATION_COORDINATES
            + " DATETIME DEFAULT CURRENT_TIMESTAMP" + ")";

    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public VisitedLocation(String address, String coordinate) {
        this.address = address;
        this.coordinate = coordinate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }
}
