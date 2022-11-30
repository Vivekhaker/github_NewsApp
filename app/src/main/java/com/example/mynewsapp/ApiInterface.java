package com.example.mynewsapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

 //   @GET("top-headlines?country=us&apiKey=e48cbd4739294a7a87ab528d040b41d7")

   @GET("top-headlines")
    Call<NewsResponse> getBasic(@Query("country") String country ,@Query("apiKey") String apiKey );

}
