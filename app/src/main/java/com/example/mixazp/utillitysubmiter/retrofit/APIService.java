package com.example.mixazp.utillitysubmiter.retrofit;

import com.example.mixazp.utillitysubmiter.model.ElectrModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {

    @POST("/posts")
    @FormUrlEncoded
    Call<ElectrModel> savePost(@Field("date") String date,
                              @Field("utiles") String utiles,
                              @Field("adress") String adress,
                              @Field("email") String email);

}

