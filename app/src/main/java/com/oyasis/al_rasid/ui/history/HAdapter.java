package com.oyasis.al_rasid.ui.history;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.oyasis.al_rasid.databinding.FragmentHistoryBinding;


import java.util.ArrayList;
import java.util.List;


public class HAdapter extends RecyclerView.Adapter<ViewHolder> {

    private  List<Speed> mValues;

    public HAdapter(List<Speed> items) {
        mValues = items;
    }

    private Context context;

    public HAdapter(Context ctx) {
        context = ctx;
        mValues = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ViewHolder viewHolder;
        viewHolder = new ViewHolder(FragmentHistoryBinding.inflate(LayoutInflater.from(context), parent, false));
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.bindView(mValues.get(position));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void updateSpeedList(List<Speed> newSpeed) {
        this.mValues = newSpeed;
        notifyDataSetChanged();
    }
}