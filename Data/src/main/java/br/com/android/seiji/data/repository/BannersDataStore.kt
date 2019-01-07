package br.com.android.seiji.data.repository

import br.com.android.seiji.data.model.BannerEntity
import io.reactivex.Completable
import io.reactivex.Flowable

interface BannersDataStore {

    fun cleanBanners(): Completable

    fun saveBanners(banners: List<BannerEntity>): Completable

    fun getBanners(): Flowable<List<BannerEntity>>
}