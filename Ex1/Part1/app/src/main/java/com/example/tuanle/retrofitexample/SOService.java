package com.example.tuanle.retrofitexample;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by tuanle on 3/4/18.
 */

public interface SOService {
    @GET("/maps/api/geocode/json")
    Call<DetailAddress> getDetail(@Query("latlng") String latlng,@Query("key") String API_KEY);
}
