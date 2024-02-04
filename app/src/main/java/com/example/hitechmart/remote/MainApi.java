package com.example.hitechmart.remote;

import com.example.hitechmart.model.ResetRequest;
import com.example.hitechmart.model.Token;
import com.example.hitechmart.model.User;
import com.example.hitechmart.model.VerifyCodeRequest;
import com.example.hitechmart.model.VerifyCodeResponse;
import com.example.hitechmart.model.VerifyEmailResponse;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MainApi {

    @POST("v1/user/")
    Call<User> createUser(@Body User user);

    @POST("v1/login/")
    Call<User> login(@Body User user);

    @GET("v1/user/verify_email/")
    Call<VerifyEmailResponse> verifyEmail(@Query("email") String email);

    @POST("v1/user/verify_email/")
    Call<VerifyCodeResponse> verifyCode(@Body VerifyCodeRequest verifyCodeRequest);

    @POST("v1/user/reset_password/")
    Call<JsonObject> resetPassword(@Header("Authorization") String access_token, @Body ResetRequest resetRequest);

}
