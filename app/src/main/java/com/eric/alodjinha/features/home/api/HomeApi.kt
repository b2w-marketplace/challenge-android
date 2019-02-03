package com.eric.alodjinha.features.home.api

import com.eric.alodjinha.base.ApiResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers

interface HomeApi {

    @Headers("Accept: application/json")
    @GET("/banner")
    fun getBanner(): Observable<ApiResponse<BannerResponse>>

    @Headers("Accept: application/json")
    @GET("/categoria")
    fun getCategories(): Observable<ApiResponse<CategoriesResponse>>

    @Headers("Accept: application/json")
    @GET("/produto/maisvendidos")
    fun getProductsMoreSallers(): Observable<ApiResponse<ProductResponse>>
}