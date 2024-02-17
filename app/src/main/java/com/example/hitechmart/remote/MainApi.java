package com.example.hitechmart.remote;

import com.example.hitechmart.model.Banner;
import com.example.hitechmart.model.Category;
import com.example.hitechmart.model.Classification;
import com.example.hitechmart.model.Product;
import com.example.hitechmart.model.ResetRequest;
import com.example.hitechmart.model.Subproduct;
import com.example.hitechmart.model.User;
import com.example.hitechmart.model.VerifyCodeRequest;
import com.example.hitechmart.model.VerifyCodeResponse;
import com.example.hitechmart.model.VerifyEmailResponse;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MainApi {

    @POST("/v1/user/")
    Call<User> createUser(@Body User user);

    @POST("v1/login/")
    Call<User> login(@Body User user);

    @GET("v1/user/verify_email/")
    Call<VerifyEmailResponse> verifyEmail(@Query("email") String email);

    @POST("v1/user/verify_email/")
    Call<VerifyCodeResponse> verifyCode(@Body VerifyCodeRequest verifyCodeRequest);

    @POST("v1/user/reset_password/")
    Call<JsonObject> resetPassword(@Header("Authorization") String access_token, @Body ResetRequest resetRequest);



    @GET("/v1/classification/{id}/category/")
    Call<ArrayList<Category>> getCategoryWithClassification(@Path("id") int classificationId);


    @GET("/v1/category/{id}/subproduct/")
    Call<ArrayList<Subproduct>> getSubproductWithCategory(@Path("id") int categoryId);


    // Home Page

    @GET("/v1/banner/")
    Call<ArrayList<Banner>> getBanners();

    @GET("/v1/popular/")
    Call<ArrayList<Product>> getPopularProducts();

    @GET("v1/classification/")
    Call<ArrayList<Classification>> getClassifications();

    // Home Page
    @GET("/v1/subproduct/{id}/products/")
    Call<ArrayList<Product>> getProducts(@Path("id") int subproductId);
}
