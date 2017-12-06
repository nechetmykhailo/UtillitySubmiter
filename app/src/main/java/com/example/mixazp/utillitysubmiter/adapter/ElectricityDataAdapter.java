package com.example.mixazp.utillitysubmiter.adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mixazp.utillitysubmiter.R;
import com.example.mixazp.utillitysubmiter.model.ElectrModel;

import java.util.ArrayList;
import java.util.List;

public class ElectricityDataAdapter extends RecyclerView.Adapter<ElectricityDataAdapter.ElectricityDataViewHolder> {

    private List<ElectrModel> electrModels;
    private SQLiteDatabase db;
    private Cursor cursor;
    private Context context;

    public ElectricityDataAdapter(Context context, List<ElectrModel> models) {
        this.electrModels = new ArrayList<>();
        this.electrModels = models;
        this.context = context;
    }

    @Override
    public ElectricityDataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.electr_adapter, parent, false);

        return new ElectricityDataViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ElectricityDataViewHolder holder, int position) {

        holder.tvDateEl.setText(electrModels.get(position).getDate());
        holder.tvUtilsEl.setText(electrModels.get(position).getUtiles());
    }

    @Override
    public int getItemCount() {
        return electrModels.size();
    }

    public class ElectricityDataViewHolder extends RecyclerView.ViewHolder{

        TextView tvDateEl;
        TextView tvUtilsEl;

        public ElectricityDataViewHolder(View itemView) {
            super(itemView);

            tvDateEl = (TextView)itemView.findViewById(R.id.tvDateEl);
            tvUtilsEl = (TextView)itemView.findViewById(R.id.tvUtilsEl);
        }
    }
}
