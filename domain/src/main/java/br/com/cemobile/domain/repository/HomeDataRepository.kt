package br.com.cemobile.domain.repository

import br.com.cemobile.domain.FetchStrategy
import br.com.cemobile.domain.model.HomeData
import br.com.cemobile.domain.model.Result

interface HomeDataRepository {

    suspend fun getHomeDataInformation(strategy: FetchStrategy): Result<HomeData>

}