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
import com.example.mixazp.utillitysubmiter.activity.WaterActivity;
import com.example.mixazp.utillitysubmiter.adapter.WaterDataAdapter;
import com.example.mixazp.utillitysubmiter.model.WaterModel;

import java.util.ArrayList;
import java.util.List;

public class WaterFragment extends Fragment {

    public static final int REQUEST_COD_WATER = 3;

    private RecyclerView rwWa;
    private FloatingActionButton fabOkWa;

    private List<WaterModel> waterModels;
    private Intent intent;

    private SQLiteConnector connector;

    private RecyclerView.Adapter mAdapterWat;
    private RecyclerView.LayoutManager mLayoutManagers;

    public WaterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_water, container, false);

        rwWa = (RecyclerView) v.findViewById(R.id.rwWa);
        fabOkWa = (FloatingActionButton) v.findViewById(R.id.fabOkWa);

        waterModels = new ArrayList<>();
        fabOkWa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getContext(), WaterActivity.class);
                startActivityForResult(intent, REQUEST_COD_WATER);
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_COD_WATER) {
            if (resultCode == WaterActivity.RESULT_OK) {

                connector = new SQLiteConnector(getActivity());
                waterModels = new ArrayList<>();
                waterModels = connector.getDataFromWat();

                mLayoutManagers = new LinearLayoutManager(getActivity());
                rwWa.setLayoutManager(mLayoutManagers);

                mAdapterWat = new WaterDataAdapter(getActivity(), waterModels);

//                mAdapterWat.notifyDataSetChanged();

                rwWa.addItemDecoration(new SpacecItemDecoration(getActivity()));
                rwWa.setAdapter(mAdapterWat);

                Toast.makeText(getActivity(), "Данные пришли", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
