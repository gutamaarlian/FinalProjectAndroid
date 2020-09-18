package com.example.myapplication.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class BustListViewHolder extends RecyclerView.ViewHolder {
    public TextView code, make;
    public CardView busCard;
    public BustListViewHolder(@NonNull View itemView) {
        super(itemView);
        busCard = itemView.findViewById(R.id.cv_bus);
        make = itemView.findViewById(R.id.txt_make);
        code = itemView.findViewById(R.id.txt_code);

    }
}
