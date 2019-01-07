package br.com.android.seiji.data.repository

import br.com.android.seiji.data.model.BannerEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface BannersCache {

    fun cleanBanners(): Completable

    fun saveBanners(banners: List<BannerEntity>): Completable

    fun getBanners(): Flowable<List<BannerEntity>>

    fun areBannersCached(): Single<Boolean>

    fun setLastCacheTime(lastCache: Long): Completable

    fun isBannersCacheExpired(): Single<Boolean>
}