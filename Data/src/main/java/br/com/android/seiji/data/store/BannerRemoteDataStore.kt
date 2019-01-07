package br.com.android.seiji.data.store

import br.com.android.seiji.data.model.BannerEntity
import br.com.android.seiji.data.repository.BannersDataStore
import br.com.android.seiji.data.repository.BannersRemote
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class BannerRemoteDataStore @Inject constructor(
    private val bannersRemote: BannersRemote
) : BannersDataStore {
    override fun cleanBanners(): Completable {
        throw UnsupportedOperationException("Clean banners isn't supported here...")
    }

    override fun saveBanners(banners: List<BannerEntity>): Completable {
        throw UnsupportedOperationException("Save banners isn't supported here...")
    }

    override fun getBanners(): Flowable<List<BannerEntity>> {
        return bannersRemote.getBanners()
    }
}