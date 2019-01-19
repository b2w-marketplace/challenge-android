package br.com.cemobile.data.source

import br.com.cemobile.domain.model.Banner

interface BannerDataSource {

    suspend fun getAllBanners(): Result<List<Banner>>

    fun save(banners: List<Banner>)

    fun deleteAllBanners()

}