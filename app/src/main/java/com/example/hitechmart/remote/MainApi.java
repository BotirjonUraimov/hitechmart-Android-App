package com.example.hitechmart.remote;

import com.example.hitechmart.model.Token;
import com.example.hitechmart.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MainApi {

    @POST("v1/user/")
    Call<User> createUser(@Body User user);

    @POST("v1/auth-token/")
    Call<User> login(@Body User user);



}
