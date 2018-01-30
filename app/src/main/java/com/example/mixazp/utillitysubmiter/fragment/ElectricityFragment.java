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

import com.example.mixazp.utillitysubmiter.R;
import com.example.mixazp.utillitysubmiter.SQLiteConnector;
import com.example.mixazp.utillitysubmiter.SpacecItemDecoration;
import com.example.mixazp.utillitysubmiter.activity.ElectricityActivity;
import com.example.mixazp.utillitysubmiter.adapter.ElectricityDataAdapter;
import com.example.mixazp.utillitysubmiter.model.ElectrModel;

import java.util.ArrayList;
import java.util.List;

public class ElectricityFragment extends Fragment {

    public static final int REQUEST_COD_ELECTRICITY = 1;

    private RecyclerView rwEl;
    private FloatingActionButton fabOkEl;

    private List<ElectrModel> electrModels;
    private Intent intent;

    private SQLiteConnector connector;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManagers;

    public ElectricityFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_electricity, container, false);

        rwEl = v.findViewById(R.id.rwEl);
        fabOkEl = v.findViewById(R.id.fabOkEl);

        electrModels = new ArrayList<>();
        fabOkEl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getActivity(), ElectricityActivity.class);
                startActivityForResult(intent, REQUEST_COD_ELECTRICITY);
            }
        });
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_COD_ELECTRICITY) {
            if (resultCode == ElectricityActivity.RESULT_OK) {
                update();
            }
        }
    }

    public void update() {
        connector = new SQLiteConnector(getActivity());
        electrModels = new ArrayList<>();
        electrModels = connector.getDataFromEl();

        mLayoutManagers = new LinearLayoutManager(getActivity());
        rwEl.setLayoutManager(mLayoutManagers);

        mAdapter = new ElectricityDataAdapter(getActivity(), electrModels);

        rwEl.addItemDecoration(new SpacecItemDecoration(getActivity()));
        rwEl.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        update();
    }
}
