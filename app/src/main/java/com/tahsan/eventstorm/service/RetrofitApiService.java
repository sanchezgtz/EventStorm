package com.tahsan.eventstorm.service;

import com.tahsan.eventstorm.pojo.LoginRequest;
import com.tahsan.eventstorm.pojo.LoginResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface  RetrofitApiService {

    @Headers("Content-Type: application/json")
    @POST("login")
    Call<LoginResponse> getLogin(
            @Body RequestBody login);

    @Headers("Content-Type: application/json")
    @POST("registeruser")
    Call<LoginResponse> registrarUsuario(
            @Body LoginRequest login);

}
