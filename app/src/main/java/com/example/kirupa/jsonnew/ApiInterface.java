package com.example.kirupa.jsonnew;

import android.icu.text.Replaceable;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("countries/")
        // API's endpoints
    Call<ResponsePogo> getData();


//    @GET("add/{id}/{name}/{password}")
//    Call<addpojo> addData(@Path("id") String id,
//                          @Path("name") String name,
//                          @Path("password") String password);
//
//    @FormUrlEncoded // annotation used in POST type requests
//    @POST("add/register.php")
//    Call<addpojo> addData(@Field("id") String id,
//                          @Field("name") String name,
//                          @Field("password") String password);
}
