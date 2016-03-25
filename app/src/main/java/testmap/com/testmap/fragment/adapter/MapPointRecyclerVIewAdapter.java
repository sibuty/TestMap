package testmap.com.testmap.fragment.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import testmap.com.testmap.R;
import testmap.com.testmap.databinding.RvMappointItemBinding;
import testmap.com.testmap.entity.MapPoint;

import java.util.List;

/**
 * Created by glotemz on 25.03.16.
 */
public class MapPointRecyclerVIewAdapter extends RecyclerView.Adapter<MapPointRecyclerVIewAdapter.MapPointViewHolder> {

    private List<MapPoint> mapPointList;

    public MapPointRecyclerVIewAdapter(final List<MapPoint> mapPointList) {
        super();
        this.mapPointList = mapPointList;
    }

    @Override
    public MapPointViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final RvMappointItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.rv_mappoint_item,
                parent,
                false);
        return new MapPointViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(final MapPointViewHolder holder, final int position) {
        holder.bind(mapPointList.get(position));
        holder.setLast(position == getItemCount() - 1);
    }

    @Override
    public int getItemCount() {
        return mapPointList.size();
    }

    public void setMapPointList(final List<MapPoint> mapPointList) {
        this.mapPointList = mapPointList;
    }

    public static class MapPointViewHolder extends RecyclerView.ViewHolder {
        private final View itemLayout;
        private final RvMappointItemBinding binding;

        public MapPointViewHolder(final View itemView) {
            super(itemView);
            this.itemLayout = itemView;
            binding = DataBindingUtil.bind(itemView);
        }

        public void setLast(final boolean isLast) {
            if (isLast) {
                itemLayout.findViewById(R.id.bottom_line).setVisibility(View.GONE);
            }
        }

        public final void bind(final MapPoint mapPoint) {
            binding.setMapPoint(mapPoint);
            binding.executePendingBindings();
        }
    }
}
