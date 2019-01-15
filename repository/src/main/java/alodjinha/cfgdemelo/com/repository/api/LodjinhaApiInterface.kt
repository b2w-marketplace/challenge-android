package alodjinha.cfgdemelo.com.repository.api

import alodjinha.cfgdemelo.com.model.*
import io.reactivex.Single
import retrofit2.http.*

interface LodjinhaApiInterface {

    @Headers("Accept: application/json")
    @GET("banner")
    fun getBanners(): Single<BannersResponse>

    @Headers("Accept: application/json")
    @GET("categoria")
    fun getCategories(): Single<CategoriesResponse>

    @Headers("Accept: application/json")
    @GET("produto/maisvendidos")
    fun getBestSellers(): Single<ProductsResponse>

    @Headers("Accept: application/json")
    @GET("produto")
    fun getProductsByCategoryId(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("categoriaId") id: Int
    ): Single<ProductsResponse>

    @Headers("Accept: application/json")
    @GET("produto/{produtoId}")
    fun getProductById(@Path("produtoId") id: Int): Single<Product>

    @Headers("Accept: application/json")
    @POST("produto/{produtoId}")
    fun bookProductById(@Path("produtoId") id: Int): Single<BookingResponse>
}