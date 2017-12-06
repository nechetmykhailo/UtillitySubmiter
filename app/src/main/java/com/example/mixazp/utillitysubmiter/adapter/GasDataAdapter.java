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
import com.example.mixazp.utillitysubmiter.model.GasModel;

import java.util.ArrayList;
import java.util.List;

public class GasDataAdapter extends RecyclerView.Adapter<GasDataAdapter.GasDataViewHolder>{

    private List<GasModel> gasModels;
    private SQLiteDatabase db;
    private Cursor cursor;
    private Context context;

    public GasDataAdapter(Context context, List<GasModel> models) {
        this.gasModels = new ArrayList<>();
        this.gasModels = models;
        this.context = context;
    }

    @Override
    public GasDataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.gas_adapter, parent, false);

        return new GasDataViewHolder(v);
    }

    @Override
    public void onBindViewHolder(GasDataViewHolder holder, int position) {

        holder.tvDateGas.setText(gasModels.get(position).getDate());
        holder.tvUtilsGas.setText(gasModels.get(position).getUtilites());
    }

    @Override
    public int getItemCount() {
        return gasModels.size();
    }

    public class GasDataViewHolder extends RecyclerView.ViewHolder{

        TextView tvDateGas;
        TextView tvUtilsGas;

        public GasDataViewHolder(View itemView) {
            super(itemView);

            tvDateGas = (TextView)itemView.findViewById(R.id.tvDateGas);
            tvUtilsGas = (TextView)itemView.findViewById(R.id.tvUtilsGas);
        }
    }
}
