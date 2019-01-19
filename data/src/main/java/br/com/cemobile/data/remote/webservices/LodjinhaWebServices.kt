package br.com.cemobile.data.remote.webservices

import br.com.cemobile.data.remote.models.*
import br.com.cemobile.domain.model.Product
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface LodjinhaWebServices {

    @GET("/banner")
    fun getBanners(): Deferred<BannerResponse>

    @GET("/categoria")
    fun getCategories(): Deferred<CategoriesResponse>

    @GET("/produto/maisvendidos")
    fun getBestSellingProducts(): Deferred<BestSellingProductsResponse>

    @GET("/produto")
    fun getProducts(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("categoriaId") categoryId: Long
    ): Deferred<ProductsResponse>

    @GET("/produto/{produtoId}")
    fun getProduct(@Path("produtoId") productId: Long): Deferred<Product>

    @POST("/produto/{produtoId}")
    fun reserve(@Path("produtoId") productId: Long): Deferred<ProductReservationResponse>

}