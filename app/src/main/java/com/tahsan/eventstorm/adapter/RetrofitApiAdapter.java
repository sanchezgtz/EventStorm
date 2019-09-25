package com.tahsan.eventstorm.adapter;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApiAdapter {
    public static final String BASE_URL ="https://longe.olinrobotclub.com/api2/public/";
    public static Retrofit retrofit;

    public static Retrofit getRetrofit(){
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL) //This is the only mandatory call on Builder object.
                    .addConverterFactory(GsonConverterFactory.create()) // Convertor library used to convert response into POJO
                    .build();

        }
        return retrofit;
    }
}
