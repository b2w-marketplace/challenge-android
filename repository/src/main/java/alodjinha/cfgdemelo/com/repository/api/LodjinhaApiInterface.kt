package alodjinha.cfgdemelo.com.repository.api

import alodjinha.cfgdemelo.com.model.BannersResponse
import alodjinha.cfgdemelo.com.model.ProductsResponse
import alodjinha.cfgdemelo.com.model.CategoriesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

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
}