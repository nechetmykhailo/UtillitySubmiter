package com.example.mixazp.utillitysubmiter.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mixazp.utillitysubmiter.R;
import com.example.mixazp.utillitysubmiter.SQLiteConnector;


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

        fabElectr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                connector = new SQLiteConnector(getApplicationContext());
                db = connector.getWritableDatabase();

                String date = etDateEl.getText().toString();
                String utills = etUtilesEl.getText().toString();
                String adresses = etAdressEl.getText().toString();
                String email = etEmailEl.getText().toString();

                if(date.equals("") || utills.equals("") || adresses.equals("") || email.equals("")){
                    Toast.makeText(getApplicationContext(), "Все поля должны быть заполнены", Toast.LENGTH_SHORT).show();
                }else {

                    connector.insertElectricity(date , utills,adresses, email);
                    setResult(RESULT_OK);
                    finish();
                }


            }
        });
    }


}
