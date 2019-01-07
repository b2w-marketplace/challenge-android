package br.com.android.seiji.data.store

import br.com.android.seiji.data.repository.BannersDataStore
import javax.inject.Inject

class BannersDataStoreFactory @Inject constructor(
    private val bannersCacheDataStore: BannerCacheDataStore,
    private val bannersRemoteDataStore: BannerRemoteDataStore
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