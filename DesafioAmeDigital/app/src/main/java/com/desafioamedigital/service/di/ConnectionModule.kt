package com.desafioamedigital.service.di

import com.desafioamedigital.service.api.BannerService
import com.desafioamedigital.service.api.CategoryService
import com.desafioamedigital.service.api.ProductService
import com.desafioamedigital.util.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ConnectionModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideClient(): OkHttpClient =
        OkHttpClient.Builder()
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()

    @Provides
    @Singleton
    fun provideBannerService(retrofit: Retrofit) = retrofit.create(BannerService::class.java)

    @Provides
    @Singleton
    fun provideCategoryService(retrofit: Retrofit) = retrofit.create(CategoryService::class.java)

    @Provides
    @Singleton
    fun provideProductService(retrofit: Retrofit) = retrofit.create(ProductService::class.java)
}