package br.com.rbueno.lodjinha.network

import br.com.rbueno.lodjinha.model.Banner
import br.com.rbueno.lodjinha.model.Category
import br.com.rbueno.lodjinha.model.ProductList
import io.reactivex.Single
import retrofit2.http.GET

interface LodjinhaApi {

    @GET("banner")
    fun getBanner(): Single<Banner>

    @GET("categoria")
    fun getCategory(): Single<Category>

    @GET("produto/maisvendidos")
    fun getProductsMostSold(): Single<ProductList>
}