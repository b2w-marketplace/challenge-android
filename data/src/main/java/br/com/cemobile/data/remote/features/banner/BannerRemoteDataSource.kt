package br.com.cemobile.data.remote.features.banner

import br.com.cemobile.domain.model.Banner
import br.com.cemobile.domain.model.Result

interface BannerRemoteDataSource {

    suspend fun getAllBanners(): Result<List<Banner>>

}