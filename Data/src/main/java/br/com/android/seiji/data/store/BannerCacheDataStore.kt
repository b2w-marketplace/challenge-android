package br.com.android.seiji.data.store

import br.com.android.seiji.data.model.BannerEntity
import br.com.android.seiji.data.repository.BannersCache
import br.com.android.seiji.data.repository.BannersDataStore
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class BannerCacheDataStore @Inject constructor(
    private val bannersCache: BannersCache
) : BannersDataStore {

    override fun cleanBanners(): Completable {
        return bannersCache.cleanBanners()
    }

    override fun saveBanners(banners: List<BannerEntity>): Completable {
        return bannersCache.saveBanners(banners)
    }

    override fun getBanners(): Flowable<List<BannerEntity>> {
        return bannersCache.getBanners()
    }
}