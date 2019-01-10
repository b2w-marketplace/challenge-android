package br.com.android.seiji.remote.service

import br.com.android.seiji.remote.model.BannerResponseModel
import br.com.android.seiji.remote.model.CategoryResponseModel
import br.com.android.seiji.remote.model.ProductResponseModel
import io.reactivex.Completable
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface LodjinhaService {

    @GET("/banner")
    fun getBanners(): Flowable<BannerResponseModel>

    @GET("/categoria")
    fun getCategories(): Flowable<CategoryResponseModel>

    @GET("/produto/maisvendidos")
    fun getBestSellerProducts(): Flowable<ProductResponseModel>

    @GET("/produto")
    fun getProductsByCategoryId(
        @Query("categoriaId") categoryId: Int,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Flowable<ProductResponseModel>

    @POST("/produto/{productId}")
    fun postProductReservation(
        @Path("productId") productId: Int
    ): Completable
}