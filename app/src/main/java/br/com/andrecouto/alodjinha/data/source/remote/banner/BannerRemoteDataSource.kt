package br.com.andrecouto.alodjinha.data.source.remote.banner

import br.com.andrecouto.alodjinha.domain.model.lodjinha.Banner
import io.reactivex.Single

interface BannerRemoteDataSource {
    fun getBanners() : Single<List<Banner>>
}