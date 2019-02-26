package br.com.andrecouto.alodjinha.domain.repository

import br.com.andrecouto.alodjinha.domain.model.lodjinha.Banner
import io.reactivex.Single

interface BannerRepository {
    fun getBanners() : Single<List<Banner>>
}