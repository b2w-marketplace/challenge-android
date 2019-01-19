package br.com.cemobile.data.remote.features.banner

import br.com.cemobile.data.remote.webservices.LodjinhaWebServices
import br.com.cemobile.domain.model.Banner
import br.com.cemobile.domain.model.Result

class BannerRemoteDataSourceImpl(private val services: LodjinhaWebServices) : BannerRemoteDataSource {

    override suspend fun getAllBanners(): Result<List<Banner>> {
        val bannerResponse = services.getBanners().await()
        return Result.Success(bannerResponse.data)
    }

}