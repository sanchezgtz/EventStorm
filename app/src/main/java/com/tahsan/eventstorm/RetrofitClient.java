package com.tahsan.eventstorm;

import com.tahsan.eventstorm.adapter.RetrofitApiAdapter;
import com.tahsan.eventstorm.service.RetrofitApiService;

public class RetrofitClient {

    public static RetrofitApiService getServiceClass(){
        return RetrofitApiAdapter.getRetrofit().create(RetrofitApiService.class);
    }
}
