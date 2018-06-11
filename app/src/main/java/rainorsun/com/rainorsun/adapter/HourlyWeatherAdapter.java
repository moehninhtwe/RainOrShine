package rainorsun.com.rainorsun.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import rainorsun.com.rainorsun.R;
import rainorsun.com.rainorsun.Util;
import rainorsun.com.rainorsun.data.api.model.HourlyWeatherData;

public class HourlyWeatherAdapter
    extends RecyclerView.Adapter<HourlyWeatherAdapter.HourlyWeatherViewHolder> {
    private HourlyWeatherData[] listOfHourlyData;

    public HourlyWeatherAdapter() {
    }

    public void setListOfHourlyData(HourlyWeatherData[] listOfHourlyData) {
        this.listOfHourlyData = listOfHourlyData;
    }

    @NonNull @Override
    public HourlyWeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.hourly_weather_view, parent, false);
        return new HourlyWeatherViewHolder(view);
    }

    @Override public void onBindViewHolder(@NonNull HourlyWeatherViewHolder holder, int position) {
        holder.tvTemperature.setText(String.valueOf(listOfHourlyData[position].getTemperature()));
        holder.tvTime.setText(
            Util.convertMilliSecondToHourFormat(listOfHourlyData[position].getTime()));
    }

    @Override public int getItemCount() {
        return listOfHourlyData.length;
    }

    public class HourlyWeatherViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivWeatherIcon;
        private TextView tvTemperature;
        private TextView tvTime;

        public HourlyWeatherViewHolder(View view) {
            super(view);
            ivWeatherIcon = view.findViewById(R.id.iv_weather_icon);
            tvTemperature = view.findViewById(R.id.tv_temperature);
            tvTime = view.findViewById(R.id.tv_time);
        }
    }
}
