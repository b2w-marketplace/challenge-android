package br.com.android.seiji.data.store.banners

import br.com.android.seiji.data.repository.banners.BannersDataStore
import javax.inject.Inject

class BannersDataStoreFactory @Inject constructor(
    private val bannersCacheDataStore: BannersCacheDataStore,
    private val bannersRemoteDataStore: BannersRemoteDataStore
) {

    open fun getDataStore(bannersCached: Boolean, cacheExpired: Boolean): BannersDataStore {
        return if (bannersCached && !cacheExpired) {
            bannersCacheDataStore
        } else {
            bannersRemoteDataStore
        }
    }

    open fun getCacheDataStore(): BannersDataStore {
        return bannersCacheDataStore
    }
}