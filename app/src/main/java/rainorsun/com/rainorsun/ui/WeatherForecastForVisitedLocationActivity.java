package rainorsun.com.rainorsun.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import rainorsun.com.rainorsun.R;
import rainorsun.com.rainorsun.ui.adapter.VisitedLocationsAdapter;
import rainorsun.com.rainorsun.sqliteDatabase.model.VisitedLocation;
import utils.DatabaseHandler;

public class WeatherForecastForVisitedLocationActivity extends AppCompatActivity
    implements VisitedLocationsAdapter.OnVisitedLocationClickListener {
    private VisitedLocationsAdapter visitedLocationsAdapter;
    private RecyclerView rvVisitedLocations;

    @Override public void onClickVisitedLocation(VisitedLocation visitedLocation) {
        Intent intent = new Intent(this, WeatherForecastActivity.class);
        intent.putExtra(WeatherForecastActivity.LOCATION, visitedLocation);
        startActivity(intent);
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visited_locations);
        rvVisitedLocations = findViewById(R.id.rv_visited_locations);
        rvVisitedLocations.setLayoutManager(
            new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvVisitedLocations.addItemDecoration(
            new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        visitedLocationsAdapter = new VisitedLocationsAdapter(this, this);
    }

    @Override protected void onResume() {
        super.onResume();
        new DatabaseHandler(this).getVisitedLocations(visitedLocations -> {
            visitedLocationsAdapter.setListOfVisitedLocations(visitedLocations);
            rvVisitedLocations.setAdapter(visitedLocationsAdapter);
        });
    }
}
