package com.example.mixazp.utillitysubmiter.activity;

import android.app.Activity;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ElectricityActivity extends Activity {

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

                if(utiles.equals("") || adress.equals("")){
                    Toast.makeText(getApplicationContext(), R.string.notNull, Toast.LENGTH_SHORT).show();
                }else if (!validatorEmail(email)){
                    Toast.makeText(getApplicationContext(), R.string.invalideEmail, Toast.LENGTH_SHORT).show();
                } else {
                    sendPost(dateTime ,utiles, adress, email);
                }
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
}
