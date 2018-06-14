package rainorsun.com.rainorsun.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import rainorsun.com.rainorsun.R;
import rainorsun.com.rainorsun.Util;
import rainorsun.com.rainorsun.WeatherDetailsActivity;
import rainorsun.com.rainorsun.WeatherForecastDetailsFragment;
import rainorsun.com.rainorsun.data.api.model.DailyWeatherData;

public class DailyWeatherAdapter
    extends RecyclerView.Adapter<DailyWeatherAdapter.DailyWeatherViewHolder> {
    private List<DailyWeatherData> listOfDailyWeather;
    private Context context;

    public DailyWeatherAdapter(Context context) {
        this.context = context;
    }

    public void setDailyWeatherData(List<DailyWeatherData> listOfDailyWeather) {
        this.listOfDailyWeather = listOfDailyWeather;
    }

    @NonNull @Override
    public DailyWeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.daily_weather_view, parent, false);
        return new DailyWeatherViewHolder(view);
    }

    @Override public void onBindViewHolder(@NonNull DailyWeatherViewHolder dailyWeatherViewHolder,
        int position) {
        dailyWeatherViewHolder.tvHighTemperature.setText(
            String.valueOf(Math.round(listOfDailyWeather.get(position).getTemperatureHigh())));
        dailyWeatherViewHolder.tvLowTemperature.setText(
            String.valueOf(Math.round(listOfDailyWeather.get(position).getTemperatureLow())));
        dailyWeatherViewHolder.tvDay.setText(
            Util.covertMilliSecondToDay(context, listOfDailyWeather.get(position).getTime()));
        dailyWeatherViewHolder.ivWeatherIcon.setImageDrawable(
            Util.getWeatherIcon(context, listOfDailyWeather.get(position).getIcon()));
        dailyWeatherViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                Intent intent = new Intent(context, WeatherDetailsActivity.class);
                intent.putExtra(WeatherForecastDetailsFragment.WEATHER_DETAILS,
                    listOfDailyWeather.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override public int getItemCount() {
        return listOfDailyWeather.size();
    }

    public class DailyWeatherViewHolder extends RecyclerView.ViewHolder {
        private TextView tvDay;
        private TextView tvHighTemperature;
        private TextView tvLowTemperature;
        private ImageView ivWeatherIcon;

        public DailyWeatherViewHolder(View view) {
            super(view);
            tvDay = view.findViewById(R.id.tv_day);
            tvHighTemperature = view.findViewById(R.id.tv_high_temperature);
            tvLowTemperature = view.findViewById(R.id.tv_low_temperature);
            ivWeatherIcon = view.findViewById(R.id.iv_weather_icon);
        }
    }
}
