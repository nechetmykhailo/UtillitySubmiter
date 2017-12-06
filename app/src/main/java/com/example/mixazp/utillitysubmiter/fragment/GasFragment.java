package com.example.mixazp.utillitysubmiter.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mixazp.utillitysubmiter.R;
import com.example.mixazp.utillitysubmiter.SQLiteConnector;
import com.example.mixazp.utillitysubmiter.SpacecItemDecoration;
import com.example.mixazp.utillitysubmiter.activity.GasActivity;
import com.example.mixazp.utillitysubmiter.adapter.GasDataAdapter;
import com.example.mixazp.utillitysubmiter.model.GasModel;

import java.util.ArrayList;
import java.util.List;

public class GasFragment extends Fragment {

    public static final int REQUEST_COD_GAS = 2;

    private RecyclerView rwGas;
    private FloatingActionButton fabOkGas;

    private List<GasModel> gasModels;
    private Intent intent;

    private SQLiteConnector connector;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManagers;

    public GasFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_gas, container, false);

        rwGas = (RecyclerView) v.findViewById(R.id.rwGas);
        fabOkGas = (FloatingActionButton) v.findViewById(R.id.fabOkGas);

        gasModels = new ArrayList<>();
        fabOkGas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getActivity(), GasActivity.class);
                startActivityForResult(intent, REQUEST_COD_GAS);
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_COD_GAS) {
            if (resultCode == GasActivity.RESULT_OK) {

                load();

            }
        }
    }

    private void load() {
        connector = new SQLiteConnector(getActivity());
        gasModels = new ArrayList<>();
        gasModels = connector.getDataFromGas();

        mLayoutManagers = new LinearLayoutManager(getActivity());
        rwGas.setLayoutManager(mLayoutManagers);

        mAdapter = new GasDataAdapter(getActivity(), gasModels);

        rwGas.addItemDecoration(new SpacecItemDecoration(getActivity()));
        rwGas.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        load();
    }

    //    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if(isVisibleToUser){
////            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
//        }
//    }

}
