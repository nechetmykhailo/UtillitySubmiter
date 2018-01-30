package com.example.mixazp.utillitysubmiter.activity;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mixazp.utillitysubmiter.R;
import com.example.mixazp.utillitysubmiter.SQLiteConnector;
import com.example.mixazp.utillitysubmiter.model.WaterModel;
import com.example.mixazp.utillitysubmiter.retrofit.ApiService;
import com.example.mixazp.utillitysubmiter.retrofit.RetrofitClient;
import com.rogerlemmonapps.captcha.Captcha;
import com.rogerlemmonapps.captcha.TextCaptcha;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WaterActivity extends Activity {

    private EditText etDateWa;
    private EditText etUtilesWa;
    private EditText etBtnUtilesWa;
    private EditText etEmailWa;
    private Button btnScanWa;
    private FloatingActionButton fabWater;

    private ImageView imageView;
    private EditText editTextCaptcha;
    private ImageButton imBtnCaptca;

    private Button btnScanEl;
    private SQLiteConnector connector;
    private SQLiteDatabase db;

    private ApiService mApiService;
    private String s;

    private Captcha c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);

        etDateWa = findViewById(R.id.etDateWa);
        etUtilesWa = findViewById(R.id.etUtilesWa);
        etBtnUtilesWa = findViewById(R.id.etBtnUtilesWa);
        etEmailWa = findViewById(R.id.etEmailWa);
        btnScanWa = findViewById(R.id.btnScanWa);
        fabWater = findViewById(R.id.fabWater);
        imageView = findViewById(R.id.ivCaptchaWater);
        editTextCaptcha = findViewById(R.id.etCaptchaWater);
        imBtnCaptca = findViewById(R.id.imBtnCaptca);

        mApiService = RetrofitClient.getApiService();
        connector = new SQLiteConnector(getApplicationContext());
        s = connector.getDateTime();
        etDateWa.setText(s);

        showCaptcha(TextCaptcha.TextOptions.LETTERS_ONLY);

        fabWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = connector.getWritableDatabase();

                String utiltesWater = etUtilesWa.getText().toString();
                String email = etEmailWa.getText().toString();
                String date = etDateWa.getText().toString();

                if (utiltesWater.equals("") || email.equals("")) {
                    Toast.makeText(getApplicationContext(), R.string.notNull, Toast.LENGTH_SHORT).show();
                } else if (!validatorEmail(email)){
                    Toast.makeText(getApplicationContext(), R.string.invalideEmail, Toast.LENGTH_SHORT).show();
                }else {
                    insertWater(date ,utiltesWater, email);
                }
            }
        });

        imBtnCaptca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCaptcha(TextCaptcha.TextOptions.LETTERS_ONLY);
            }
        });
    }

    private void insertWater(final String date, final String utiltesWater, final String emailWat) {
        mApiService.waterPost(date, utiltesWater, emailWat).enqueue(new Callback<WaterModel>() {
            @Override
            public void onResponse(Call<WaterModel> call, Response<WaterModel> response) {
                if (response.isSuccessful()) {
                    c = (Captcha) imageView.getTag();
                    if(c.checkAnswer(editTextCaptcha.getText().toString())){
                        connector.insertWater(date ,utiltesWater, emailWat);
                        setResult(RESULT_OK);
                        finish();
                    }else {
                        Toast.makeText(getApplication(), R.string.captcha, Toast.LENGTH_SHORT).show();
                        return;
                    }
                } else {
                    Toast.makeText(getApplication(), R.string.exeption, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<WaterModel> call, Throwable t) {
                Toast.makeText(getApplication(), R.string.exeption, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showCaptcha(TextCaptcha.TextOptions type) {
        c = new TextCaptcha(400,150, 4,type);
        imageView.setImageBitmap(c.getImage());
        imageView.setTag(c);
    }

    private boolean validatorEmail(String email) {
        String regExpn = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        return matcher.matches();
    }
}
