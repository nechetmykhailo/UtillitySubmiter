package com.example.mixazp.utillitysubmiter.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mixazp.utillitysubmiter.R;
import com.example.mixazp.utillitysubmiter.activity.WaterActivity;
import com.example.mixazp.utillitysubmiter.model.WaterModel;

import java.util.ArrayList;
import java.util.List;

public class WaterDataAdapter extends RecyclerView.Adapter<WaterDataAdapter.WaterDataViewHolder>{

    private List<WaterModel> waterModels;
    private SQLiteDatabase db;
    private Cursor cursor;
    private Context context;

    public WaterDataAdapter(Context context, List<WaterModel> models) {
        this.waterModels = new ArrayList<>();
        this.waterModels = models;
        this.context = context;
    }

    @Override
    public WaterDataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.water_adapter, parent, false);

        return new WaterDataViewHolder(v);
    }

    @Override
    public void onBindViewHolder(WaterDataViewHolder holder, int position) {

        holder.tvDateWater.setText(waterModels.get(position).getDateWater());
        holder.tvUtilsWater.setText(waterModels.get(position).getUtilesWat());
    }

    @Override
    public int getItemCount() {
        return waterModels.size();
    }

    public class WaterDataViewHolder extends RecyclerView.ViewHolder{

        TextView tvDateWater;
        TextView tvUtilsWater;

        public WaterDataViewHolder(View itemView) {
            super(itemView);

            tvDateWater = (TextView)itemView.findViewById(R.id.tvDateWater);
            tvUtilsWater = (TextView)itemView.findViewById(R.id.tvUtilsWater);
        }
    }
}
