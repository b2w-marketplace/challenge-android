package com.b2w.lodjinha.data.api

import com.b2w.lodjinha.data.model.*
import io.reactivex.Observable
import retrofit2.http.*

interface LodjinhaAPI {

    @GET(value = "banner")
    fun fetchBanners(): Observable<BannerResponse>

    @GET(value = "categoria")
    fun fetchCategories(): Observable<CategoryResponse>

    @GET(value = "produto")
    fun fetchProducts(
        @Header(value = "categoriaId") categoryId: Int,
        @Header(value = "offset") offset: Int,
        @Header(value = "limit") limit: Int
    ): Observable<ProductsResponse>

    @POST(value = "produto/{produtoId}")
    fun doReservation(
        @Path("produtoId") productId: Int
    ): Observable<NoContentResponse>

    @GET(value = "produto/maisvendidos")
    fun fetchBestSelling(): Observable<BestSellingResponse>
}