package com.tahsan.eventstorm.service;

import com.tahsan.eventstorm.pojo.LoginResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface  RetrofitApiService {

    @GET("login")
    Call<LoginResponse> getLogin(
            @Query("username") String username,
            @Query("password") String password
    );
}
