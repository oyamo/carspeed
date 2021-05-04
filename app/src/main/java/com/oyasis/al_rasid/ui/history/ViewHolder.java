package com.oyasis.al_rasid.ui.history;

import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.oyasis.al_rasid.databinding.FragmentHistoryBinding;

public class ViewHolder extends RecyclerView.ViewHolder {

    private FragmentHistoryBinding b;
    public ViewHolder(FragmentHistoryBinding binding) {
        super(binding.getRoot());
        this.b = binding;
    }

    void bindView(Speed speed) {
        b.from.setText(String.format("FROM:%s", speed.getMovingFrom()));
        b.to.setText(String.format("TO:%s", speed.getMovingTo()));
        b.numberPlate.setText(speed.getNumberPlate());
        b.speed.setText(String.format("%sKM/H", speed.getSpeed()));
    }
}
