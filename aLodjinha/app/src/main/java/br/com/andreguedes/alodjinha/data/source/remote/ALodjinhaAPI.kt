package br.com.andreguedes.alodjinha.data.source.remote

import br.com.andreguedes.alodjinha.data.model.BannerResponse
import br.com.andreguedes.alodjinha.data.model.Category
import br.com.andreguedes.alodjinha.data.model.Product
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ALodjinhaAPI {

    @GET("/banner")
    fun getBanners(): Observable<BannerResponse>

    @GET("/categoria")
    fun getCategories(): Observable<List<Category>>

    @GET("/produto")
    fun getProducts(
            @Query("offset") offset: Int,
            @Query("limit") limit: Int,
            @Query("categoriaId") categoriaId: Int
    ): Observable<List<Product>>

    @GET("/produto/maisvendidos")
    fun getProductsBestSellers(): Observable<List<Product>>

    @GET("/produto/{produtoId}")
    fun getProduct(
            @Path("produtoId") produtoId: Int
    ) : Observable<Product>

    @POST("/produto/{produtoId}")
    fun reserveProduct(
            @Path("produtoId") produtoId: Int
    ) : Observable<Void>

}