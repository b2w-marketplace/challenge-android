package br.com.android.seiji.data.repository.product

import br.com.android.seiji.data.model.ProductEntity
import io.reactivex.Completable
import io.reactivex.Flowable

interface ProductsDataStore {

    fun getBestSellerProducts(): Flowable<List<ProductEntity>>

    fun getProductsByCategoryId(categoryId: Int, offset: Int, limit: Int): Flowable<List<ProductEntity>>

    fun postProductReservation(productId: Int): Completable

    fun saveBestSellerProducts(bestSellerProducts: List<ProductEntity>): Completable

    fun clearBestSellerProducts(): Completable
}