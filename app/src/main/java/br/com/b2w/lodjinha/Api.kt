package br.com.b2w.lodjinha

import br.com.b2w.lodjinha.features.banner.BannerResponse
import br.com.b2w.lodjinha.features.category.CategoryResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface Api {

    @GET("banner")
    fun getBanners() : Deferred<BannerResponse>

    @GET("categoria")
    fun getCategories(): Deferred<CategoryResponse>

}