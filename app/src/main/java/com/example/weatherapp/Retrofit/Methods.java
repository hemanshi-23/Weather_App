package com.example.weatherapp.Retrofit;

import com.example.weatherapp.ModelClass.Root;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface Methods {
    @Headers({"X-RapidAPI-Key: dd801d3c8cmsh9fb022f2cf749edp17993fjsn725c81a85bf6"})

    @GET("current.json")
    Call<Root> getAllData(@Query("q") String str);
}
