package br.com.cemobile.domain.repository

import br.com.cemobile.domain.FetchStrategy
import br.com.cemobile.domain.model.Banner
import br.com.cemobile.domain.model.Result

interface BannerRepository {

    suspend fun getAllBanners(strategy: FetchStrategy): Result<List<Banner>>

}