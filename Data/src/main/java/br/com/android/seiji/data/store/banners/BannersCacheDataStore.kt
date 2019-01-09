package br.com.android.seiji.data.store.banners

import br.com.android.seiji.data.model.BannerEntity
import br.com.android.seiji.data.repository.banners.BannersCache
import br.com.android.seiji.data.repository.banners.BannersDataStore
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class BannersCacheDataStore @Inject constructor(
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