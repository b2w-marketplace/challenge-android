import br.com.android.seiji.cache.db.CacheDatabase
import br.com.android.seiji.cache.mapper.CachedBannerMapper
import br.com.android.seiji.cache.model.CacheConfig
import br.com.android.seiji.data.model.BannerEntity
import br.com.android.seiji.data.repository.BannersCache
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class BannersCacheImpl @Inject constructor(
    private val database: CacheDatabase,
    private val mapper: CachedBannerMapper
) : BannersCache {

    override fun cleanBanners(): Completable {
        return Completable.defer {
            database.cachedBannerDao().deleteBanners()
            Completable.complete()
        }
    }

    override fun saveBanners(banners: List<BannerEntity>): Completable {
        return Completable.defer {
            database.cachedBannerDao().insertBanners(banners.map { mapper.mapToCached(it) })
            Completable.complete()
        }
    }

    override fun getBanners(): Flowable<List<BannerEntity>> {
        return database.cachedBannerDao().getBanners()
            .map {
                it.map { mapper.mapFromCached(it) }
            }
    }

    override fun areBannersCached(): Single<Boolean> {
        return database.cachedBannerDao().getBanners().isEmpty
            .map { !it }
    }

    override fun setLastCacheTime(lastCache: Long): Completable {
        return Completable.defer {
            database.cacheConfigDao().insertCacheConfig(CacheConfig(lastCacheTime = lastCache))
            Completable.complete()
        }
    }

    override fun isBannersCacheExpired(): Single<Boolean> {
        val currentTime = System.currentTimeMillis()
        val expirationTime = (60 * 10 * 1000).toLong()
        return database.cacheConfigDao().getCacheConfig()
            .onErrorReturn { CacheConfig(lastCacheTime = 0) }
            .toSingle(CacheConfig(lastCacheTime = 0L))
            .map { currentTime - it.lastCacheTime > expirationTime }
    }
}