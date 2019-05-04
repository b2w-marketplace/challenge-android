package com.mmart.sidershopping.model.api;

import com.mmart.sidershopping.model.ALodjinhaAPI;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "https://alodjinha.herokuapp.com";
    private static RetrofitClient mInstace;
    private Retrofit retrofit;


    private RetrofitClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();


    }


    public static synchronized RetrofitClient getInstance() {
        if (mInstace == null) {
            mInstace = new RetrofitClient();
        }

        return mInstace;
    }

    public ALodjinhaAPI api() {

        return retrofit.create(ALodjinhaAPI.class);
    }
}
