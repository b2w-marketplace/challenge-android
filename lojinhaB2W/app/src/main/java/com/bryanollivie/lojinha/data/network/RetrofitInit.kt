package com.bryanollivie.lojinha.data.network

import com.bryanollivie.lojinha.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInit {

    val BASE_URL = "https://alodjinha.herokuapp.com/"

    private var retrofit: Retrofit? = null

    val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    val clientOkHttp: OkHttpClient?
        get() {
            return if (BuildConfig.DEBUG)
                OkHttpClient.Builder().addInterceptor(interceptor).build()
            else
                OkHttpClient.Builder().build()
        }

    val client: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(clientOkHttp)
                        .build()
            }
            return retrofit
        }

}
