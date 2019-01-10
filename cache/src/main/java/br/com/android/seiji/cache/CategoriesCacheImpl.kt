package br.com.android.seiji.cache

import br.com.android.seiji.cache.db.CacheDatabase
import br.com.android.seiji.cache.mapper.CachedCategoryMapper
import br.com.android.seiji.cache.model.CacheConfig
import br.com.android.seiji.data.model.CategoryEntity
import br.com.android.seiji.data.repository.category.CategoriesCache
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class CategoriesCacheImpl @Inject constructor(

        private val database: CacheDatabase,
        private val mapper: CachedCategoryMapper

) : CategoriesCache {

    override fun clearCategories(): Completable {
        return Completable.defer {
            database.cachedCategoryDao().deleteCategories()
            Completable.complete()
        }
    }

    override fun saveCategories(categories: List<CategoryEntity>): Completable {
        return Completable.defer {
            database.cachedCategoryDao().insertCategories(categories.map { mapper.mapToCached(it) })
            Completable.complete()
        }
    }

    override fun getCategories(): Flowable<List<CategoryEntity>> {
        return database.cachedCategoryDao().getCategories()
                .map {
                    it.map { mapper.mapFromCached(it) }
                }
    }


    override fun areCategoriesCached(): Single<Boolean> {
        return database.cachedCategoryDao().getCategories().isEmpty
                .map { !it }
    }


    override fun setLastCategoriesCacheTime(lastCache: Long): Completable {
        return Completable.defer {
            database.cacheConfigDao().insertCacheConfig(CacheConfig(lastCacheTime = lastCache))
            Completable.complete()
        }
    }


    override fun isCategoriesCacheExpired(): Single<Boolean> {

        val currentTime = System.currentTimeMillis()
        val expirationTime = (60 * 10 * 1000).toLong()

        return database.cacheConfigDao().getCacheConfig()
                .onErrorReturn { CacheConfig(lastCacheTime = 0) }
                .toSingle(CacheConfig(lastCacheTime = 0L))
                .map { currentTime - it.lastCacheTime > expirationTime }
    }
}