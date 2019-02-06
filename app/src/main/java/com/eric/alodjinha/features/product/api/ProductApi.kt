package com.eric.alodjinha.features.product.api

import com.eric.alodjinha.features.product.model.Product
import io.reactivex.Observable
import retrofit2.http.*

interface ProductApi {

    @Headers("Accept: application/json")
    @GET("/produto")
    fun getProductsByCategory(
        @Query("offset") offset: Int,
        @Query("limite") limite: Int,
        @Query("categoriaId") categoriaId: Int
    ): Observable<ProductResponse>

    @Headers("Accept: application/json")
    @POST("/produto/{produtoId}")
    fun productReservation(@Path("produtoId") productId: Int): Observable<ProductReservationResponse>

    @Headers("Accept: application/json")
    @GET("/produto/{produtoId}")
    fun getProductDetail(@Path("produtoId") productId: Int): Observable<Product>
}