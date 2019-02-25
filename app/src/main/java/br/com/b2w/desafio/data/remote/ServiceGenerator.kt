package br.com.b2w.desafio.data.remote

import br.com.b2w.desafio.util.Constantes
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object ServiceGenerator {

    private val httpClient = OkHttpClient.Builder()

    private val builder = Retrofit.Builder()
        .addConverterFactory(getGson())
        .baseUrl(Constantes.API_BASE_URL)
        .client(getClient())

    private var retrofit = builder.build()

    fun <S> createService(serviceClass: Class<S>): S {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(httpLoggingInterceptor)

        builder.client(httpClient.build())
        retrofit = builder.build()

        return retrofit.create(serviceClass)
    }

    private fun getClient(): OkHttpClient {
        return OkHttpClient.Builder().connectTimeout(15, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    private fun getGson(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }
}