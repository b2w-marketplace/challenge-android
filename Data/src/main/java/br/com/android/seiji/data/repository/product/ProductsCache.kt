package br.com.android.seiji.data.repository.product

import br.com.android.seiji.data.model.ProductEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface ProductsCache {

    fun getBestSellerProducts(): Flowable<List<ProductEntity>>
    fun saveBestSellerProducts(bestSellerProducts: List<ProductEntity>): Completable
    fun clearBestSellerProducts(): Completable

    fun areProductsCached(): Single<Boolean>
    fun setLastCacheTime(lastCache: Long): Completable
    fun isCacheExpired(): Single<Boolean>
}