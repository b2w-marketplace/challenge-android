package com.danilodequeiroz.webapi

import com.google.gson.GsonBuilder
import com.itkacher.okhttpprofiler.OkHttpProfilerInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

fun providesLivrRulesRetrofitClient(): Retrofit {
    val okhttp = OkHttpClient.Builder()

    if (BuildConfig.DEBUG) {
        okhttp.addInterceptor(OkHttpProfilerInterceptor())
        okhttp.addInterceptor(HttpLoggingInterceptor().apply { this.level = HttpLoggingInterceptor.Level.BODY })
    }

    return Retrofit.Builder()
        .client(okhttp.build())
        .baseUrl("https://alodjinha.herokuapp.com/")
        .addConverterFactory(providesGsonConverter())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
}

private fun providesGsonConverter(): GsonConverterFactory {
    val gsonBuilder = GsonBuilder()
    return GsonConverterFactory.create(gsonBuilder.create())
}