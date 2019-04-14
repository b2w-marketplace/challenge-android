package br.com.dafle.alodjinha.service

import br.com.dafle.alodjinha.model.Product
import br.com.dafle.alodjinha.model.ProductResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductService {

    @GET("/produto/")
    fun all(@Query("offset") offset: Int,
            @Query("limit") limit: Int,
            @Query("categoriaId") categoriaId: Int): Observable<ProductResponse>

    @GET("/produto/maisvendidos")
    fun fetchBestSeller(): Observable<ProductResponse>

    @GET("/produto/{produtoId}")
    fun get(@Path("produtoId") produtoId: Int): Observable<Product>

    @POST("/produto/{produtoId}")
    fun reserve(@Path("produtoId") produtoId: Int): Observable<Unit>
}

