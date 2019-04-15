package br.com.dafle.alodjinha.koin

import br.com.dafle.alodjinha.Environment
import br.com.dafle.alodjinha.service.HomeService
import br.com.dafle.alodjinha.service.ProductService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.mockito.Mockito
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object CreateModulesTest {

    private fun getClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        return httpClient.build()
    }

    fun provideGson(): Gson {
        return GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .setLenient()
                .create()
    }

    fun provideRetrofitInterface(gson: Gson): Retrofit {
        return Retrofit.Builder()
                .baseUrl(Environment.baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getClient())
                .build()
    }

    fun provideCoinsService(retrofit: Retrofit): ProductService {
        return Mockito.mock(ProductService::class.java)
    }

    fun provideHomeService(retrofit: Retrofit): HomeService {
        return Mockito.mock(HomeService::class.java)
    }
}