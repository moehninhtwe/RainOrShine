package rainorsun.com.rainorsun;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import rainorsun.com.rainorsun.adapter.VisitedLocationsAdapter;

public class WeatherForecastForVisitedLocationActivity extends AppCompatActivity {
    private VisitedLocationsAdapter visitedLocationsAdapter;
    private RecyclerView rvVisitedLocations;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visited_locations);
        rvVisitedLocations = findViewById(R.id.rv_visited_locations);
        rvVisitedLocations.setLayoutManager(
            new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        visitedLocationsAdapter = new VisitedLocationsAdapter(this);
    }

    @Override protected void onResume() {
        super.onResume();
        new DatabaseHandler(this).getVisitedLocations(visitedLocations -> {
            visitedLocationsAdapter.setListOfVisitedLocations(visitedLocations);
            rvVisitedLocations.setAdapter(visitedLocationsAdapter);
        });
    }
}
