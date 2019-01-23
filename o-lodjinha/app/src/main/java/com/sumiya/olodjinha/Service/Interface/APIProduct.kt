package com.sumiya.olodjinha.Service.Interface

import com.sumiya.olodjinha.Model.ProductDataModel
import com.sumiya.olodjinha.Model.ProductModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface APIProduct {
    @GET("produto")
    fun list(@Query("offset") offset: Int,
             @Query("limit") limit: Int,
             @Query("categoriaId") categoriaId: Int): Call<ProductDataModel>

    @GET("produto/maisvendidos")
    fun listTop(): Call<ProductDataModel>

    @GET("produto/{produtoId}")
    fun get(@Path("produtoId") produtoId: Int): Call<ProductModel>

    @POST("produto/{produtoId}")
    fun post(@Path("produtoId") produtoId: Int): Call<String>
}