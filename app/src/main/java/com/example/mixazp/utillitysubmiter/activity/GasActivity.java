package com.example.mixazp.utillitysubmiter.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mixazp.utillitysubmiter.R;
import com.example.mixazp.utillitysubmiter.SQLiteConnector;
import com.example.mixazp.utillitysubmiter.model.GasModel;
import com.example.mixazp.utillitysubmiter.retrofit.ApiService;
import com.example.mixazp.utillitysubmiter.retrofit.RetrofitClient;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GasActivity extends Activity {

    private EditText etDateGas;
    private EditText etUtilesGas;
    private EditText etPassGas;
    private EditText etEmailGas;
    private Button btnScanGas;
    private FloatingActionButton fabGas;

    private SQLiteConnector connector;
    private SQLiteDatabase db;

    private String s;
    private ApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gas);

        etDateGas = findViewById(R.id.etDateGas);
        etUtilesGas = findViewById(R.id.etUtilesGas);
        etPassGas = findViewById(R.id.etPassGas);
        etEmailGas = findViewById(R.id.etEmailGas);
        btnScanGas = findViewById(R.id.btnScanGas);
        fabGas = findViewById(R.id.fabGas);

        mApiService = RetrofitClient.getApiService();
        connector = new SQLiteConnector(getApplicationContext());
        s = connector.getDateTime();
        etDateGas.setText(s);

        fabGas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db = connector.getWritableDatabase();

                String utilitiesGas = etUtilesGas.getText().toString();
                String email = etEmailGas.getText().toString();
                String passwordGas = etPassGas.getText().toString();
                String date = etDateGas.getText().toString();

                if (utilitiesGas.equals("") || email.equals("") || passwordGas.equals("")) {
                    Toast.makeText(getApplicationContext(), R.string.notNull, Toast.LENGTH_SHORT).show();
                } else if (!validatorEmail(email)){
                    Toast.makeText(getApplicationContext(), R.string.invalideEmail, Toast.LENGTH_SHORT).show();
                }else {
                    insertGas(date ,utilitiesGas, passwordGas, email);
                }


            }
        });
    }

    private void insertGas(final String date ,final String utilitiesGas, final String passwordGas, final String emailGas) {

        mApiService.gasPost(date, utilitiesGas, passwordGas, emailGas).enqueue(new Callback<GasModel>() {
            @Override
            public void onResponse(Call<GasModel> call, Response<GasModel> response) {
                if (response.isSuccessful()) {
                    connector.insertGas(date, utilitiesGas, passwordGas, emailGas);
                    setResult(RESULT_OK);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.exeption, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GasModel> call, Throwable t) {

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
