package com.example.myapplication.service;

import com.example.myapplication.entity.User;

import okhttp3.Callback;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UserService {
    @Multipart
    @POST ("user/login")
    Call<String> login(@Part("email")RequestBody email, @Part("password") RequestBody password);

    @POST ("user/createUser")
    Call<User> register(@Body User user);

    @POST("user/checkEmailUserByUser")
    Call<User> checkEmail(@Body User userParam);

    @Multipart
    @POST("user/getUserById")
    Call<User> getUserById(@Part("userId")RequestBody userId);



}
