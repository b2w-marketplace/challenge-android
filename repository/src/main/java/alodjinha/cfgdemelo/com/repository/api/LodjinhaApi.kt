package alodjinha.cfgdemelo.com.repository.api

import alodjinha.cfgdemelo.com.model.*
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
    open fun getBestSellers(): Single<ProductsResponse> = apiInterface.getBestSellers()
    open fun getProductsByCategoryId(offset: Int, limit: Int, id: Int): Single<ProductsResponse> =
        apiInterface.getProductsByCategoryId(offset, limit, id)
    open fun getProductById(id: Int): Single<Product> = apiInterface.getProductById(id)
    open fun bookProduct(id: Int): Single<BookingResponse> = apiInterface.bookProductById(id)
}