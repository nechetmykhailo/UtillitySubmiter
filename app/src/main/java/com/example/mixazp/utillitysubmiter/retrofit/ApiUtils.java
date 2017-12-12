package com.example.mixazp.utillitysubmiter.retrofit;

public class ApiUtils {

    private ApiUtils() {}

    // вписываем сайт куда отправляем данные
    public static final String BASE_URL = "http://jsonplaceholder.typicode.com/";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
