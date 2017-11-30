package com.example.mixazp.utillitysubmiter;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.mixazp.utillitysubmiter.model.ElectrModel;
import com.example.mixazp.utillitysubmiter.model.GasModel;
import com.example.mixazp.utillitysubmiter.model.WaterModel;

import java.util.ArrayList;
import java.util.List;

public class SQLiteConnector extends SQLiteOpenHelper {

    private SQLiteDatabase db;

    Context context;

    public SQLiteConnector(Context context) {
        super(context, "Utilles", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try{
            sqLiteDatabase.execSQL("create table Electricity (_id integer primary key autoincrement,"
                    + "dateEl varchar(100),"
                    + "utilElectr varchar(100),"
                    + "adressEl nvarchar(100),"
                    + "email varchar(100) )");

            sqLiteDatabase.execSQL("create table Water (_id integer primary key autoincrement,"
                    + "dateWater varchar(100),"
                    + "utilWater varchar(100),"
                    + "adressWater nvarchar(100),"
                    + "emailWater varchar(100) )");

            sqLiteDatabase.execSQL("create table Gas (_id integer primary key autoincrement,"
                    + "dateGas varchar(100),"
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
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()){
            do {
                ElectrModel modelEl = new ElectrModel();
                modelEl.setDateEl(cursor.getString(1));
                modelEl.setUtilesEl(cursor.getString(2));
                modelEl.setAdressEl(cursor.getString(3));
                modelEl.setEmailEl(cursor.getString(4));

                modelList.add(modelEl);
            }while (cursor.moveToNext());
        }

        return modelList;
    }

    // Добавление в БД Water
    public void insertWater(String dateWater ,String utilWater, String adressWater, String emailWater){

        db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("dateWater", dateWater);
        values.put("utilWater", utilWater);
        values.put("adressWater", adressWater);
        values.put("emailWater", emailWater);

        db.insert("Water", null, values);

        db.close();
    }

    // Читаем курсором данные из БД
    public List<WaterModel> getDataFromWat(){
        List<WaterModel> modelListWter = new ArrayList<>();
        String query = "select * from Water";

        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()){
            do {
                WaterModel modelWat = new WaterModel();
                
                modelWat.setDateWater(cursor.getString(1));
                modelWat.setUtilesWat(cursor.getString(2));
                modelWat.setAdressWat(cursor.getString(3));
                modelWat.setEmailWat(cursor.getString(4));

                modelListWter.add(modelWat);
            }while (cursor.moveToNext());
        }

        return modelListWter;

    }

    // Добавление в БД Gas
    public void insertGas(String dateGas, String utilitiesGas, String emailGas, String passwordGas){

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
    public List<GasModel> getDataFromGas(){
        List<GasModel> modelListGas = new ArrayList<>();
        String query = "select * from Gas";

        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()){
            do {
                GasModel model = new GasModel();

                model.setGasDate(cursor.getString(1));
                model.setGasUtilites(cursor.getString(2));
                model.setGasEmail(cursor.getString(3));
                model.setGasPassword(cursor.getString(4));

                modelListGas.add(model);
            }while (cursor.moveToNext());
        }
        return modelListGas;
    }
}
