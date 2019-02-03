package com.eric.alodjinha.features.product.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {

    @Headers("Accept: application/json")
    @GET("/produto")
    fun getProductsByCategory(
        @Query("offset") offset: Int,
        @Query("limite") limite: Int,
        @Query("categoriaId") categoriaId: Int
    ): Observable<ProductResponse>
}