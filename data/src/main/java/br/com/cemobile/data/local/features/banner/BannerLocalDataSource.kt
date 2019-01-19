package br.com.cemobile.data.local.features.banner

import br.com.cemobile.domain.model.Banner
import br.com.cemobile.domain.model.Result

interface BannerLocalDataSource {

    suspend fun getAllBanners(): Result<List<Banner>>

    suspend fun saveBanners(banners: List<Banner>)

    suspend fun deleteAllBanners()

}