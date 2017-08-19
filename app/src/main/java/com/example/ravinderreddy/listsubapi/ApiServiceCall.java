package com.example.ravinderreddy.listsubapi;

import com.google.gson.JsonObject;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;
import retrofit.http.Query;

public interface ApiServiceCall
{
    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET("/upgrade/api/categories/")
    public abstract void listMain(Callback<JsonObject> callback);



    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET("/upgrade/api/categories/{catId}/products")
    public abstract void listSub(@Path("catId") String catId ,
                                 @Query("products")  String proudcts,
                                 @Query("status") String status,
                                 @Query("items_per_page") String item_per_page,
                                 @Query("page")  String page,
                                 Callback<JsonObject> callback);
}