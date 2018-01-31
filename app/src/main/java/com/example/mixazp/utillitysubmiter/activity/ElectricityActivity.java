package com.example.mixazp.utillitysubmiter.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mixazp.utillitysubmiter.R;
import com.example.mixazp.utillitysubmiter.SQLiteConnector;
import com.example.mixazp.utillitysubmiter.model.ElectrModel;
import com.example.mixazp.utillitysubmiter.retrofit.ApiService;
import com.example.mixazp.utillitysubmiter.retrofit.RetrofitClient;
import com.example.mixazp.utillitysubmiter.scan.ScanActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ElectricityActivity extends Activity {

    private static final int REQUEST_COD_SCAN_ELECTRICITY = 1;
    private EditText etDateEl;
    private EditText etUtilesEl;
    private EditText etBtnUtilesEl;
    private EditText etAdressEl;
    private EditText etEmailEl;

    private Button btnScanEl;
    private FloatingActionButton fabElectr;
    private SQLiteConnector connector;
    private SQLiteDatabase db;

    private String s;

    private ApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electricity);

        etDateEl = findViewById(R.id.etDateEl);
        etUtilesEl = findViewById(R.id.etUtilesEl);
        etBtnUtilesEl = findViewById(R.id.etBtnUtilesEl);
        etAdressEl = findViewById(R.id.etAdressEl);
        etEmailEl = findViewById(R.id.etEmailEl);
        btnScanEl = findViewById(R.id.btnScanEl);
        fabElectr = findViewById(R.id.fabElectr);

        mApiService = RetrofitClient.getApiService();

        connector = new SQLiteConnector(getApplicationContext());
        s = connector.getDateTime();
        etDateEl.setText(s);

        fabElectr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = connector.getWritableDatabase();

                String utiles = etUtilesEl.getText().toString();
                String adress = etAdressEl.getText().toString();
                String email = etEmailEl.getText().toString().trim();
                String dateTime = etDateEl.getText().toString();
                String utilesScann = etBtnUtilesEl.getText().toString();

                if((utiles.equals("") || utilesScann.equals("")) && adress.equals("")){
                    Toast.makeText(getApplicationContext(), R.string.notNull, Toast.LENGTH_SHORT).show();
                }else if (!validatorEmail(email)){
                    Toast.makeText(getApplicationContext(), R.string.invalideEmail, Toast.LENGTH_SHORT).show();
                } else {
                    if (utilesScann.equals("")) {
                        sendPost(dateTime ,utiles, adress, email);
                    } else {
                        sendPostScann(dateTime ,utilesScann, adress, email);
                    }
                }
            }
        });

        btnScanEl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ElectricityActivity.this, ScanActivity.class);
                startActivityForResult(intent, REQUEST_COD_SCAN_ELECTRICITY);
            }
        });
    }

    private void sendPostScann(final String dateTime, final String utilesScann, final String adress, final String email) {
        mApiService.savePost(dateTime, utilesScann, adress, email).enqueue(new Callback<ElectrModel>() {
            @Override
            public void onResponse(Call<ElectrModel> call, Response<ElectrModel> response) {
                if (response.isSuccessful()) {
                    connector.insertElectricity(dateTime ,utilesScann, adress, email);
                    setResult(RESULT_OK);
                    finish();
                } else {
                }
            }

            @Override
            public void onFailure(Call<ElectrModel> call, Throwable t) {

            }
        });
    }

    private void sendPost(final String dateTime , final String utiles, final String adress, final String email) {
        mApiService.savePost(dateTime, utiles, adress, email).enqueue(new Callback<ElectrModel>() {
            @Override
            public void onResponse(Call<ElectrModel> call, Response<ElectrModel> response) {
                if (response.isSuccessful()) {
                    connector.insertElectricity(dateTime ,utiles, adress, email);
                    setResult(RESULT_OK);
                    finish();
                } else {
                }
            }

            @Override
            public void onFailure(Call<ElectrModel> call, Throwable t) {

            }
        });
    }

    private boolean validatorEmail(String email) {
        String regExpn = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        return matcher.matches();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_COD_SCAN_ELECTRICITY) {
            if (resultCode == ScanActivity.RESULT_OK) {

                String str = data.getStringExtra("scan");

                etBtnUtilesEl.setText(str);
            }
        }
    }
}
