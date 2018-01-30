package com.example.mixazp.utillitysubmiter;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mixazp.utillitysubmiter.model.ElectrModel;
import com.example.mixazp.utillitysubmiter.model.GasModel;
import com.example.mixazp.utillitysubmiter.model.WaterModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SQLiteConnector extends SQLiteOpenHelper {

    private SQLiteDatabase db;
    private Cursor cursor;
    Context context;

    public SQLiteConnector(Context context) {
        super(context, "Utilles", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try{
            sqLiteDatabase.execSQL("create table Electricity (_id integer primary key autoincrement,"
                    + "dateEl DATETIME DEFAULT CURRENT_DATE,"
                    + "utilElectr varchar(100),"
                    + "adressEl varchar(100),"
                    + "email varchar(100) )");

            sqLiteDatabase.execSQL("create table Water (_id integer primary key autoincrement,"
                    + "dateWater DATETIME DEFAULT CURRENT_DATE,"
                    + "utilWater varchar(100),"
                    + "adressWater varchar(100),"
                    + "emailWater varchar(100) )");

            sqLiteDatabase.execSQL("create table Gas (_id integer primary key autoincrement,"
                    + "dateGas DATETIME DEFAULT CURRENT_DATE,"
                    + "utilitiesGas varchar(100),"
                    + "emailGas varchar(100),"
                    + "passwordGas varchar(100) )" );
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Electricity");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Water");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Gas");

        onCreate(sqLiteDatabase);
    }

    // Добавление в БД Electricity
    public void insertElectricity(String dateEl, String utilElectr, String adressEl, String email){

        db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("dateEl", dateEl);
        values.put("utilElectr", utilElectr);
        values.put("adressEl", adressEl);
        values.put("email", email);

        db.insert("Electricity", null, values);

        db.close();
    }

    // Читаем курсором данные из БД
    public List<ElectrModel> getDataFromEl(){
        List<ElectrModel> modelList = new ArrayList<>();
        String query = "select * from Electricity";

        db = this.getWritableDatabase();
        cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()){
            do {
                ElectrModel modelEl = new ElectrModel();
                modelEl.setDate(cursor.getString(1));
                modelEl.setUtiles(cursor.getString(2));
                modelEl.setAdress(cursor.getString(3));
                modelEl.setEmail(cursor.getString(4));

                modelList.add(modelEl);
            }while (cursor.moveToNext());
        }

        return modelList;
    }

    // Добавление в БД Water
    public void insertWater(String dateWater ,String utilWater, String emailWater){

        db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("dateWater", dateWater);
        values.put("utilWater", utilWater);
        values.put("emailWater", emailWater);

        db.insert("Water", null, values);

        db.close();
    }

    // Читаем курсором данные из БД
    public List<WaterModel> getDataFromWat(){
        List<WaterModel> modelListWter = new ArrayList<>();
        String query = "select * from Water";

        db = this.getWritableDatabase();
        cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()){
            do {
                WaterModel modelWat = new WaterModel();
                
                modelWat.setDate(cursor.getString(1));
                modelWat.setUtiles(cursor.getString(2));
                modelWat.setAdress(cursor.getString(3));
                modelWat.setEmail(cursor.getString(4));

                modelListWter.add(modelWat);
            }while (cursor.moveToNext());
        }

        return modelListWter;

    }

    // Добавление в БД Gas
    public void insertGas(String dateGas ,String utilitiesGas, String passwordGas, String emailGas){

        db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("dateGas", dateGas);
        values.put("utilitiesGas", utilitiesGas);
        values.put("emailGas", emailGas);
        values.put("passwordGas", passwordGas);

        db.insert("Gas", null, values);

        db.close();
    }

    // Читаем курсором данные из БД
    public List<GasModel> getDataFromGas() {
        try {
            List<GasModel> modelListGas = new ArrayList<>();
            String query = "select * from Gas";

            db = this.getWritableDatabase();
            cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {
                    GasModel model = new GasModel();

                    model.setDate(cursor.getString(1));
                    model.setUtilites(cursor.getString(2));
                    model.setEmail(cursor.getString(3));
                    model.setPassword(cursor.getString(4));

                    modelListGas.add(model);
                } while (cursor.moveToNext());
            }
            return modelListGas;
        }finally {
            cursor.close();
        }
    }

    public String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd.MM.yyyy", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}
