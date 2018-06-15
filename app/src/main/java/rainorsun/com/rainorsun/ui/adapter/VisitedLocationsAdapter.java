package rainorsun.com.rainorsun.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;
import utils.DatabaseHandler;
import rainorsun.com.rainorsun.R;
import rainorsun.com.rainorsun.sqliteDatabase.model.VisitedLocation;

public class VisitedLocationsAdapter
    extends RecyclerView.Adapter<VisitedLocationsAdapter.VisitedLocationsViewHolder> {
    private List<VisitedLocation> listOfVisitedLocations;
    private Context context;
    private OnVisitedLocationClickListener onVisitedLocationClickListener;

    public VisitedLocationsAdapter(Context context,
        OnVisitedLocationClickListener onVisitedLocationClickListener) {
        this.context = context;
        this.onVisitedLocationClickListener = onVisitedLocationClickListener;
    }

    public void setListOfVisitedLocations(List<VisitedLocation> listOfVisitedLocations) {
        this.listOfVisitedLocations = listOfVisitedLocations;
    }

    @NonNull @Override
    public VisitedLocationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.visited_locations_view, parent, false);
        return new VisitedLocationsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VisitedLocationsViewHolder holder, int position) {
        holder.tvVisitedLocation.setText(listOfVisitedLocations.get(position).getAddress());
        holder.btnRemove.setOnClickListener(
            view -> new DatabaseHandler(context).removeVisitedLocation(
                listOfVisitedLocations.get(position), () -> {
                    listOfVisitedLocations.remove(position);
                    notifyDataSetChanged();
                }));
        holder.itemView.setOnClickListener(
            view -> onVisitedLocationClickListener.onClickVisitedLocation(
                listOfVisitedLocations.get(position)));
    }

    @Override public int getItemCount() {
        return listOfVisitedLocations.size();
    }

    public class VisitedLocationsViewHolder extends RecyclerView.ViewHolder {
        private TextView tvVisitedLocation;
        private Button btnRemove;

        public VisitedLocationsViewHolder(View view) {
            super(view);
            tvVisitedLocation = view.findViewById(R.id.tv_visited_location);
            btnRemove = view.findViewById(R.id.btn_remove);
        }
    }

    public interface OnVisitedLocationClickListener {
        void onClickVisitedLocation(VisitedLocation visitedLocation);
    }
}
