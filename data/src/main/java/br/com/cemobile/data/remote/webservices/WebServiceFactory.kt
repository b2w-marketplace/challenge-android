package br.com.cemobile.data.remote.webservices

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object WebServiceFactory {

    private const val BASE_URL = "https://alodjinha.herokuapp.com"
    private const val DEFAULT_TIMEOUT_SECONDS = 20L

    fun create(
            apiURL: String = BASE_URL,
            debuggable: Boolean = false
    ): LodjinhaWebServices {
        val logger = createLogger(debuggable)
        val httpClient = createHttpClient(logger = logger)
        val converter = GsonConverterFactory.create()
        val coroutineAdapter = CoroutineCallAdapterFactory.invoke()

        val retrofit = Retrofit.Builder()
                .baseUrl(apiURL)
                .client(httpClient)
                .addConverterFactory(converter)
                .addCallAdapterFactory(coroutineAdapter)
                .build()

        return retrofit.create(LodjinhaWebServices::class.java)
    }

    private fun createLogger(debuggable: Boolean): Interceptor {
        val loggingLevel = if (debuggable) Level.BODY else Level.NONE
        return HttpLoggingInterceptor().apply { level = loggingLevel }
    }

    private fun createHttpClient(logger: Interceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(logger)
            .connectTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.MILLISECONDS)
            .build()

}