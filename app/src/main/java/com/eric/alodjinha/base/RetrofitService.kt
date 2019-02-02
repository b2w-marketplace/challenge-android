package com.eric.alodjinha.base

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitService<T>(clss: Class<T>, baseUrl: String) {

    var apiService: T
    private var authOkHttpClient: OkHttpClient
    private var retrofit: Retrofit

    init {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.HEADERS

        val logging2 = HttpLoggingInterceptor()


        logging2.level = HttpLoggingInterceptor.Level.BODY
        authOkHttpClient = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .addInterceptor(logging2)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(authOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        apiService = retrofit.create(clss)
    }
}