package br.com.android.seiji.cache

import br.com.android.seiji.cache.db.CacheDatabase
import br.com.android.seiji.cache.mapper.CachedProductMapper
import br.com.android.seiji.cache.model.CacheConfig
import br.com.android.seiji.data.model.ProductEntity
import br.com.android.seiji.data.repository.product.ProductsCache
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject


class ProductsCacheImpl @Inject constructor(

        private val database: CacheDatabase,
        private val mapper: CachedProductMapper

) : ProductsCache {

    override fun clearBestSellerProducts(): Completable {
        return Completable.defer {
            database.cachedProductDao().deleteProducts()
            Completable.complete()
        }
    }

    override fun saveBestSellerProducts(products: List<ProductEntity>): Completable {
        return Completable.defer {
            database.cachedProductDao().insertProducts(products.map { mapper.mapToCached(it) })
            Completable.complete()
        }
    }

    override fun getBestSellerProducts(): Flowable<List<ProductEntity>> {
        return database.cachedProductDao().getProducts()
                .map {
                    it.map { mapper.mapFromCached(it) }
                }
    }

    override fun areProductsCached(): Single<Boolean> {
        return database.cachedProductDao().getProducts().isEmpty
                .map { !it }
    }


    override fun setLastCacheTime(lastCache: Long): Completable {
        return Completable.defer {
            database.cacheConfigDao().insertCacheConfig(CacheConfig(lastCacheTime = lastCache))
            Completable.complete()
        }
    }

    override fun isCacheExpired(): Single<Boolean> {

        val currentTime = System.currentTimeMillis()
        val expirationTime = (60 * 10 * 1000).toLong()

        return database.cacheConfigDao().getCacheConfig()
                .onErrorReturn { CacheConfig(lastCacheTime = 0) }
                .toSingle(CacheConfig(lastCacheTime = 0L))
                .map { currentTime - it.lastCacheTime > expirationTime }
    }

}