package rainorsun.com.rainorsun.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import rainorsun.com.rainorsun.data.api.model.DailyWeatherData;

public class DailyWeatherAdapter
    extends RecyclerView.Adapter<DailyWeatherAdapter.DailyWeatherViewHolder> {
    private DailyWeatherData[] listOfDailyWeather;

    public DailyWeatherAdapter() {
    }

    public void setDailyWeatherData(DailyWeatherData[] listOfDailyWeather) {
        this.listOfDailyWeather = listOfDailyWeather;
    }

    @NonNull @Override
    public DailyWeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return null;
    }

    @Override public void onBindViewHolder(@NonNull DailyWeatherViewHolder holder, int position) {

    }

    @Override public int getItemCount() {
        return listOfDailyWeather.length;
    }

    public class DailyWeatherViewHolder extends RecyclerView.ViewHolder {
        private TextView tvDay;
        private ImageView ivWeatherIcon;
        private TextView tvTemperature;

        public DailyWeatherViewHolder(View view) {
            super(view);
        }
    }
}
