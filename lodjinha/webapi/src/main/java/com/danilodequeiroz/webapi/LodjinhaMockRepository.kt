package com.danilodequeiroz.webapi

import com.danilodequeiroz.webapi.model.banner.BannersPayload
import com.danilodequeiroz.webapi.model.category.ProductCategoriesPayload
import com.danilodequeiroz.webapi.model.post.ReserveProductPayload
import com.danilodequeiroz.webapi.model.product.BestSellingProductsPayload
import com.danilodequeiroz.webapi.model.product.Product
import com.danilodequeiroz.webapi.model.product.ProductsPayload
import io.reactivex.Single

class LodjinhaMockRepository : LodjinhaRestRepository {
    override fun getBanners(): Single<BannersPayload> {
        return Single.just(bannersPOJOmodel())
    }

    override fun getProductCategories(): Single<ProductCategoriesPayload> {
        return Single.just(categoryHomeMocks())
    }

    override fun getProducts(categoryId: Int, offset: Int, limit: Int): Single<ProductsPayload> {
        return Single.just(productsListMocks())
    }

    override fun getBestSellingProducts(): Single<BestSellingProductsPayload> {
        return Single.just(productsHomeMocks())
    }

    override fun getProduct(id: Int): Single<Product> {
        return Single.just(productDetail)
    }

    override fun reserveProduct(id: Int): Single<ReserveProductPayload> {
        return Single.just(reservePayload)
    }


}