package marcus.com.br.b2wtest.model.api

import io.reactivex.Flowable
import marcus.com.br.b2wtest.model.data.BannerResult
import marcus.com.br.b2wtest.model.data.CategoryResult
import marcus.com.br.b2wtest.model.data.ProductResult
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    //BANNER
    @GET("banner")
    fun getBanners(): Flowable<BannerResult>

    //CATEGORY
    @GET("categoria")
    fun getCategories(): Flowable<CategoryResult>

    //PRODUCT
    @GET("produto/maisvendidos")
    fun getBestSellers(): Flowable<ProductResult>

    @GET("produto")
    fun getByCategory(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int = 20,
        @Query("categoriaId") categoryId: Int
    ): Flowable<ProductResult>
}