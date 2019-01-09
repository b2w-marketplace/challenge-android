package br.com.android.seiji.data.store.banners

import br.com.android.seiji.data.mapper.BannerMapper
import br.com.android.seiji.data.repository.banners.BannersCache
import br.com.android.seiji.domain.model.Banner
import br.com.android.seiji.domain.repository.BannerRepository
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class BannersDataRepository @Inject constructor(
    private val mapper: BannerMapper,
    private val cache: BannersCache,
    private val factory: BannersDataStoreFactory
) : BannerRepository {

    override fun getBanners(): Observable<List<Banner>> {
        return Observable.zip(cache.areBannersCached().toObservable(),
            cache.isBannersCacheExpired().toObservable(),
            BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>> { areCached, isExpired ->
                Pair(areCached, isExpired)
            })
            .flatMap {
                factory.getDataStore(it.first, it.second).getBanners()
                    .toObservable().distinctUntilChanged()
            }
            .flatMap { banners ->
                factory.getCacheDataStore()
                    .saveBanners(banners)
                    .andThen(Observable.just(banners))
            }
            .map {
                it.map {
                    mapper.mapFromEntity(it)
                }
            }
    }
}