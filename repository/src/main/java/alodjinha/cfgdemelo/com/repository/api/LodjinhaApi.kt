package alodjinha.cfgdemelo.com.repository.api

import alodjinha.cfgdemelo.com.model.BannersResponse
import alodjinha.cfgdemelo.com.model.BestSellersResponse
import alodjinha.cfgdemelo.com.model.CategoriesResponse
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

open class LodjinhaApi {
    private val baseUrl = "https://alodjinha.herokuapp.com"

    private val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val httpClient = OkHttpClient.Builder().addInterceptor(logging)

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient.build())
        .build()

    private val apiInterface = retrofit.create(LodjinhaApiInterface::class.java)

    open fun getBanners(): Single<BannersResponse> = apiInterface.getBanners()
    open fun getCategories(): Single<CategoriesResponse> = apiInterface.getCategories()
    open fun getBestSellers(): Single<BestSellersResponse> = apiInterface.getBestSellers()
}