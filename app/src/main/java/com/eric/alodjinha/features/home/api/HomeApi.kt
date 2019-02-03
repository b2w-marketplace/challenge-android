package com.eric.alodjinha.features.home.api

import com.eric.alodjinha.features.product.api.ProductResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers

interface HomeApi {

    @Headers("Accept: application/json")
    @GET("/banner")
    fun getBanner(): Observable<BannerResponse>

    @Headers("Accept: application/json")
    @GET("/categoria")
    fun getCategories(): Observable<CategoriesResponse>

    @Headers("Accept: application/json")
    @GET("/produto/maisvendidos")
    fun getProductsMoreSallers(): Observable<ProductResponse>
}