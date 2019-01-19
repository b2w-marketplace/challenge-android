package br.com.cemobile.domain.features.banner

import br.com.cemobile.domain.FetchStrategy
import br.com.cemobile.domain.model.Banner
import br.com.cemobile.domain.model.Result
import br.com.cemobile.domain.repository.BannerRepository

class GetBannersImpl(private val repository: BannerRepository): GetBanners {

    override suspend fun getBanners(strategy: FetchStrategy): Result<List<Banner>> =
        repository.getAllBanners(strategy)

}