package br.com.cemobile.domain.features.banner

import br.com.cemobile.domain.FetchStrategy
import br.com.cemobile.domain.model.Banner
import br.com.cemobile.domain.model.Result

interface GetBanners {

    suspend fun getBanners(strategy: FetchStrategy): Result<List<Banner>>

}