package com.example.mixazp.utillitysubmiter;

import com.example.mixazp.utillitysubmiter.model.ElectrModel;

import retrofit2.Callback;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface ElectrInterf {

    public interface API {
        @POST("/v1/registration")
        void getUserInfo(@Path("id") int id, Callback<ElectrModel> cb);
    }
}
