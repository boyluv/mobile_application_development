package com.example.tuanle.retrofitexample;

/**
 * Created by tuanle on 3/4/18.
 */

public class ApiUtils {
    public static final String BASE_URL = "https://maps.googleapis.com/";

    public static SOService getSOService() {
        return RetrofitClient.getClient(BASE_URL).create(SOService.class);
    }
}
