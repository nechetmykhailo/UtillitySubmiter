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

public class GasActivity extends Activity {

    private EditText etDateGas;
    private EditText etUtilesGas;
//    private EditText etBtnUtilesGas;
    private EditText etPassGas;
    private EditText etEmailGas;
    private Button btnScanGas;
    private FloatingActionButton fabGas;

    private SQLiteConnector connector;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gas);

        etDateGas = (EditText) findViewById(R.id.etDateGas);
        etUtilesGas = (EditText) findViewById(R.id.etUtilesGas);
        etPassGas = (EditText) findViewById(R.id.etPassGas);
        etEmailGas = (EditText) findViewById(R.id.etEmailGas);
        btnScanGas = (Button) findViewById(R.id.btnScanGas);
        fabGas = (FloatingActionButton) findViewById(R.id.fabGas);

        fabGas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connector = new SQLiteConnector(getApplicationContext());
                db = connector.getWritableDatabase();

//                String dateGas = etDateGas.getText().toString();
                String utilitiesGas = etUtilesGas.getText().toString();
                String emailGas = etEmailGas.getText().toString();
                String passwordGas = etPassGas.getText().toString();

                connector.insertGas(utilitiesGas, emailGas, passwordGas);

                setResult(RESULT_OK);
                finish();

            }
        });
    }
}
