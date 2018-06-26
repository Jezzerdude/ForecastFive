package com.example.forecastfive.summary;

import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.forecastfive.databinding.ItemSummaryBinding;
import com.example.forecastfive.model.SummaryDataModel;

import java.util.ArrayList;
import java.util.List;

public class SummaryAdapter extends RecyclerView.Adapter<SummaryAdapter.SummaryViewHolder> {

    private final List<SummaryDataModel> data;

    SummaryAdapter() {
        data = new ArrayList<>();
    }

    public void updateData(@NonNull List<SummaryDataModel> predictions) {
        this.data.clear();
        this.data.addAll(predictions);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SummaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new SummaryViewHolder(ItemSummaryBinding.inflate(layoutInflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SummaryViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class SummaryViewHolder extends RecyclerView.ViewHolder {
        final ItemSummaryBinding itemSummaryBinding;

        SummaryViewHolder(ItemSummaryBinding itemSummaryBinding) {
            super(itemSummaryBinding.getRoot());
            this.itemSummaryBinding = itemSummaryBinding;
        }

        void bind(SummaryDataModel dataModel) {
            itemSummaryBinding.setModel(dataModel);
            itemSummaryBinding.executePendingBindings();
        }

        @BindingAdapter({"imageUrl"})
        public static void getWeatherIcon(ImageView imageView, String imageUrl) {
            if (imageUrl != null) {
                Glide.with(imageView.getContext())
                        .load(imageUrl)
                        .into(imageView);
            } else {
                Glide.with(imageView.getContext()).clear(imageView);
            }
        }
    }
}
