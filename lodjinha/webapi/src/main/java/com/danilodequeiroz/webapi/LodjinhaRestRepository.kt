package com.danilodequeiroz.webapi

import com.danilodequeiroz.webapi.model.banner.BannersPayload
import com.danilodequeiroz.webapi.model.category.ProductCategoriesPayload
import com.danilodequeiroz.webapi.model.post.ReserveProductPayload
import com.danilodequeiroz.webapi.model.product.BestSellingProductsPayload
import com.danilodequeiroz.webapi.model.product.Product
import com.danilodequeiroz.webapi.model.product.ProductsPayload
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface LodjinhaRestRepository{

    @GET("banner")
    fun getBanners(): Single<BannersPayload>

    @GET("categoria")
    fun getProductCategories(): Single<ProductCategoriesPayload>

    @GET("produto")
    fun getProducts(@Query("categoriaId") categoryId:Int, @Query("offset") offset: Int, @Query("limit") limit:Int): Single<ProductsPayload>

    @GET("produto/maisvendidos")
    fun getBestSellingProducts(): Single<BestSellingProductsPayload>

    @GET("produto/{productId}")
    fun getProduct(@Path("productId") id:Int): Single<Product>

    @POST("produto/{productId}")
    fun reserveProduct(@Path("productId") id:Int): Single<ReserveProductPayload>
}
