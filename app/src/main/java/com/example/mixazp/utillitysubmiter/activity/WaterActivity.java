package com.example.mixazp.utillitysubmiter.activity;

import android.app.Activity;
import android.content.Intent;
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
import com.rogerlemmonapps.captcha.Captcha;
import com.rogerlemmonapps.captcha.TextCaptcha;

public class WaterActivity extends Activity {

    private EditText etDateWa;
    private EditText etUtilesWa;
    private EditText etBtnUtilesWa;
    private EditText etAdressWa;
    private EditText etEmailWa;
    private Button btnScanWa;
    private FloatingActionButton fabWater;

    private ImageView imageView;
    private EditText editTextCaptcha;
    private Button btnCaptcha;
    private ImageButton imBtnCaptca;

    private Button btnScanEl;
    private SQLiteConnector connector;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);

        etDateWa = (EditText) findViewById(R.id.etDateWa);
        etUtilesWa = (EditText) findViewById(R.id.etUtilesWa);
        etBtnUtilesWa = (EditText) findViewById(R.id.etBtnUtilesWa);
//        etAdressWa = (EditText) findViewById(R.id.etAdressWa);
        etEmailWa = (EditText) findViewById(R.id.etEmailWa);
        btnScanWa = (Button) findViewById(R.id.btnScanWa);
        fabWater = (FloatingActionButton) findViewById(R.id.fabWater);

        imageView = (ImageView) findViewById(R.id.ivCaptchaWater);
        editTextCaptcha = (EditText)findViewById(R.id.etCaptchaWater);
        btnCaptcha = (Button)findViewById(R.id.btnCaptchaWater);
        imBtnCaptca = (ImageButton) findViewById(R.id.imBtnCaptca);

        showCaptcha(TextCaptcha.TextOptions.LETTERS_ONLY);

        fabWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connector = new SQLiteConnector(getApplicationContext());
                db = connector.getWritableDatabase();

                String dateWater = etDateWa.getText().toString();
                String utiltesWater = etUtilesWa.getText().toString();
                String dateWat = etDateWa.getText().toString();
                String emailWat = etEmailWa.getText().toString();

                connector.insertWater(dateWater, utiltesWater, dateWat, emailWat);

                setResult(RESULT_OK);
                finish();

                Toast.makeText(getApplicationContext(), "Данные добавлены", Toast.LENGTH_SHORT).show();
            }
        });

        btnCaptcha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Captcha c = (Captcha) imageView.getTag();

                if(c.checkAnswer(editTextCaptcha.getText().toString())){
                    Toast.makeText(WaterActivity.this, "Верно", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(WaterActivity.this, "ОЩИБКА", Toast.LENGTH_SHORT).show();
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

    private void showCaptcha(TextCaptcha.TextOptions type) {
        Captcha c = new TextCaptcha(400,150, 4,type);
        imageView.setImageBitmap(c.getImage());
        imageView.setTag(c);
    }
}
