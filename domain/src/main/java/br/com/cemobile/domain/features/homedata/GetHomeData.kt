package br.com.cemobile.domain.features.homedata

import br.com.cemobile.domain.FetchStrategy
import br.com.cemobile.domain.model.HomeData
import br.com.cemobile.domain.model.Result

interface GetHomeData {

    suspend fun getHomeDataInformation(strategy: FetchStrategy): Result<HomeData>

}