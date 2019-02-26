package br.com.andrecouto.alodjinha.data.source.remote.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceFactory {

    fun makeLodjinhaService() : Service {
        val okHttpClient = makeOkHttpClient(makeLoggingInterceptor())
        return makeLodjinhaService(okHttpClient)
    }

    private fun makeLodjinhaService(okHttpClient: OkHttpClient) : Service {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://alodjinha.herokuapp.com")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        return retrofit.create(Service::class.java)
    }

    private fun makeOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor) : OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()
    }

    private fun makeLoggingInterceptor() : HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        HttpLoggingInterceptor.Level.BODY
        return logging
    }
}