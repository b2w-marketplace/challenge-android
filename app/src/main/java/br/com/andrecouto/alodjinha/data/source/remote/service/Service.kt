package br.com.andrecouto.alodjinha.data.source.remote.service

import br.com.andrecouto.alodjinha.data.source.remote.service.response.BannerResponse
import br.com.andrecouto.alodjinha.data.source.remote.service.response.CategoriesResponse
import br.com.andrecouto.alodjinha.data.source.remote.service.response.ProductsResponse
import br.com.andrecouto.alodjinha.data.source.remote.service.response.TopSellingProductsResponse
import br.com.andrecouto.alodjinha.domain.model.lodjinha.Product
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*

interface Service {

    @GET("/banner")
    fun getBanners() : Single<BannerResponse>

    @GET("/categoria")
    fun getCategories() : Single<CategoriesResponse>

    @GET("/produto")
    fun getProducts( @Query("categoriaId") categoryId: Int) : Single<ProductsResponse>

    @GET("/produto/maisvendidos")
    fun getTopSellingProducts() : Single<TopSellingProductsResponse>

    @GET("/produto/{produtoId}")
    fun getProductDetailById(@Path("produtoId") produtoId : Int) : Single<Product>

    @POST("/produto/{produtoId}")
    fun retainProduct(@Path("produtoId") produtoId : Int) : Completable

}