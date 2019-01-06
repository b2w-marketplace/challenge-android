package br.com.android.seiji.domain.repository

import br.com.android.seiji.domain.model.Banner
import io.reactivex.Observable

interface BannerRepository {
    fun getBanners(): Observable<List<Banner>>
}