package br.com.android.seiji.data.repository.banners

import br.com.android.seiji.data.model.BannerEntity
import io.reactivex.Flowable

interface BannersRemote {
    fun getBanners(): Flowable<List<BannerEntity>>
}