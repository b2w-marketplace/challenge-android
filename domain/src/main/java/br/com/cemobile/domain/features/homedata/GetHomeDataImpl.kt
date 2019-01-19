package br.com.cemobile.domain.features.homedata

import br.com.cemobile.domain.FetchStrategy
import br.com.cemobile.domain.model.HomeData
import br.com.cemobile.domain.model.Result
import br.com.cemobile.domain.repository.HomeDataRepository

class GetHomeDataImpl(private val repository: HomeDataRepository) : GetHomeData {

    override suspend fun getHomeDataInformation(strategy: FetchStrategy): Result<HomeData> =
        repository.getHomeDataInformation(strategy)

}