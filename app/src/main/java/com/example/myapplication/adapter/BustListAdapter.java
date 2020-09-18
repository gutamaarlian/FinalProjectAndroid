package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.ViewHolder.BustListViewHolder;
import com.example.myapplication.entity.Bus;

import java.util.List;

public class BustListAdapter extends RecyclerView.Adapter<BustListViewHolder> {

    private List<Bus> busList;

    public BustListAdapter(List<Bus> busList) {
        this.busList = busList;
    }

    @NonNull
    @Override
    public BustListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new BustListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_bus, parent, false));


    }

    @Override
    public void onBindViewHolder(@NonNull BustListViewHolder holder, int position) {
        holder.code.setText(busList.get(position).getCode());
        holder.make.setText(busList.get(position).getMake());
//        holder.code.setText(busList.get(position).getCode());

    }

    @Override
    public int getItemCount() {
        return busList.size();
    }
}
