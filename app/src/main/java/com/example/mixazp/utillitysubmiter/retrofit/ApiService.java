package com.example.mixazp.utillitysubmiter.retrofit;

import com.example.mixazp.utillitysubmiter.model.ElectrModel;
import com.example.mixazp.utillitysubmiter.model.GasModel;
import com.example.mixazp.utillitysubmiter.model.WaterModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    @POST("/posts")
    @FormUrlEncoded
    Call<ElectrModel> savePost(@Field("date") String date,
                              @Field("utiles") String utiles,
                              @Field("adress") String adress,
                              @Field("email") String email);

    @POST("/posts")
    @FormUrlEncoded
    Call<GasModel> gasPost(@Field("date") String date,
                            @Field("utiles") String utiles,
                            @Field("password") String password,
                            @Field("email") String email);

    @POST("/posts")
    @FormUrlEncoded
    Call<WaterModel> waterPost(@Field("date") String date,
                              @Field("utiles") String utiles,
                              @Field("email") String email);

}

