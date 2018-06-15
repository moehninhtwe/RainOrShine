package rainorsun.com.rainorsun.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import rainorsun.com.rainorsun.R;
import utils.Util;
import rainorsun.com.rainorsun.data.api.model.DailyWeatherData;

public class DailyWeatherAdapter
    extends RecyclerView.Adapter<DailyWeatherAdapter.DailyWeatherViewHolder> {
    private List<DailyWeatherData> listOfDailyWeather;
    private Context context;
    private OnWeatherForecastClickListener onWeatherForecastClickListener;

    public DailyWeatherAdapter(Context context,
        OnWeatherForecastClickListener onWeatherForecastClickListener) {
        this.context = context;
        this.onWeatherForecastClickListener = onWeatherForecastClickListener;
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
            Util.covertMilliSecondToDay(listOfDailyWeather.get(position).getTime()));
        dailyWeatherViewHolder.ivWeatherIcon.setImageDrawable(
            Util.getWeatherIcon(context, listOfDailyWeather.get(position).getIcon()));
        dailyWeatherViewHolder.itemView.setOnClickListener(
            view -> onWeatherForecastClickListener.onWeatherForecastClick(view,
                listOfDailyWeather.get(position)));
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

    public interface OnWeatherForecastClickListener {
        void onWeatherForecastClick(View view, DailyWeatherData dailyWeatherData);
    }
}
