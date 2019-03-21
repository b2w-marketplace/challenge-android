package br.com.rbueno.lodjinha.network

import android.util.Log
import io.reactivex.exceptions.OnErrorNotImplementedException
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory.createWithScheduler
import retrofit2.converter.gson.GsonConverterFactory
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit

object NetworkConfig {

    private const val TIMEOUT = 30L
    private const val TAG = ">>> RxJava"

    fun createApi(baseUrl: String): LodjinhaApi {
        configRxDefaultErrorHandler()
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(getHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(createWithScheduler(Schedulers.io()))
            .build()
            .create(LodjinhaApi::class.java)

    }


    private fun configRxDefaultErrorHandler() {
        RxJavaPlugins.setErrorHandler { error ->
            when (error) {
                is NullPointerException,
                is IllegalArgumentException,
                is OnErrorNotImplementedException,
                is UnknownHostException,
                is InterruptedException -> {  // stream was interrupted by dispose call
                    Log.w(TAG, error.message, error)
                }
            }
        }
    }

    private fun getHttpClient() =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .build()
}