package com.example.mixazp.utillitysubmiter.activity;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mixazp.utillitysubmiter.R;
import com.example.mixazp.utillitysubmiter.SQLiteConnector;
import com.example.mixazp.utillitysubmiter.model.ElectrModel;
import com.example.mixazp.utillitysubmiter.retrofit.APIService;
import com.example.mixazp.utillitysubmiter.retrofit.ApiUtils;

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

    private APIService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electricity);

        etDateEl = (EditText) findViewById(R.id.etDateEl);
        etUtilesEl = (EditText) findViewById(R.id.etUtilesEl);
        etBtnUtilesEl = (EditText) findViewById(R.id.etBtnUtilesEl);
        etAdressEl = (EditText) findViewById(R.id.etAdressEl);
        etEmailEl = (EditText) findViewById(R.id.etEmailEl);
        btnScanEl = (Button) findViewById(R.id.btnScanEl);
        fabElectr = (FloatingActionButton) findViewById(R.id.fabElectr);

        mApiService = ApiUtils.getAPIService();

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

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if(utiles.equals("")
                        || adress.equals("")
                        || email.matches(emailPattern)){
                    Toast.makeText(getApplicationContext(), "Все поля должны быть заполнены", Toast.LENGTH_SHORT).show();
                }else {
                    sendPost(dateTime ,utiles, adress, email);
                }
            }
        });
    }

    private void sendPost(final String dateTime , final String utiles, final String adress, final String email) {
        mApiService.savePost(dateTime, utiles, adress, email).enqueue(new Callback<ElectrModel>() {
            @Override
            public void onResponse(Call<ElectrModel> call, Response<ElectrModel> response) {

                connector.insertElectricity(dateTime ,utiles, adress, email);
                setResult(RESULT_OK);
                finish();

                Log.d("LOL", response.body().toString());
            }

            @Override
            public void onFailure(Call<ElectrModel> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "Ошибка в Отправке в ретрофите", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
