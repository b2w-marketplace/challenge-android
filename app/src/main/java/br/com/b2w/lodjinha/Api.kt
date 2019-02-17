package br.com.b2w.lodjinha

import br.com.b2w.lodjinha.features.banner.BannerResponse
import br.com.b2w.lodjinha.features.category.CategoryResponse
import br.com.b2w.lodjinha.features.product.ProductsResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface Api {

    @GET("banner")
    fun getBanners() : Deferred<BannerResponse>

    @GET("categoria")
    fun getCategories(): Deferred<CategoryResponse>

    @GET("produto/maisvendidos")
    fun getBestSellerProducts(): Deferred<ProductsResponse>

}