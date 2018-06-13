package rainorsun.com.rainorsun.adapter;

import android.content.Context;
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
    private Context context;

    public HourlyWeatherAdapter(Context context) {
        this.context = context;
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

    @Override public void onBindViewHolder(@NonNull HourlyWeatherViewHolder hourlyWeatherViewHolder,
        int position) {
        hourlyWeatherViewHolder.tvTemperature.setText(
            String.valueOf(Math.round(listOfHourlyData[position].getTemperature()))
                + (char) 0x00B0);
        hourlyWeatherViewHolder.tvTime.setText(
            Util.convertMilliSecondToHourFormat(listOfHourlyData[position].getTime()));
        hourlyWeatherViewHolder.ivWeatherIcon.setImageDrawable(
            Util.getWeatherIcon(context, listOfHourlyData[position].getIcon()));
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
