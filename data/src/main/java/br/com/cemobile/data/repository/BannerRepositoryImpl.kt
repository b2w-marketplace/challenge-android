package br.com.cemobile.data.repository

import br.com.cemobile.data.local.errors.RemoteDataNotFoundException
import br.com.cemobile.data.local.features.banner.BannerLocalDataSource
import br.com.cemobile.data.remote.features.banner.BannerRemoteDataSource
import br.com.cemobile.domain.FetchStrategy
import br.com.cemobile.domain.FetchStrategy.ForceUpdate
import br.com.cemobile.domain.FetchStrategy.FromPrevious
import br.com.cemobile.domain.model.Banner
import br.com.cemobile.domain.model.Result
import br.com.cemobile.domain.repository.BannerRepository
import br.com.cemobile.lodjinha.ui.base.errors.NetworkErrorHandler

class BannerRepositoryImpl(
    private val remote: BannerRemoteDataSource,
    private val local: BannerLocalDataSource
) : BannerRepository {

    override suspend fun getAllBanners(strategy: FetchStrategy): Result<List<Banner>> = when (strategy) {
        is ForceUpdate -> fromRemoteThenCache()
        is FromPrevious -> fromCache()
    }

    private suspend fun fromRemoteThenCache(): Result<List<Banner>> =
        try {
            val result = remote.getAllBanners()
            when (result) {
                is Result.Success -> {
                    local.saveBanners(result.data)
                    result
                }
                is Result.Error -> Result.Error(RemoteDataNotFoundException())
            }
        } catch (error: Throwable) {
            if (NetworkErrorHandler.checkIfNetworkError(error)) {
                Result.Error(NetworkErrorHandler.mapToNetworkError(error))
            } else {
                Result.Error(error)
            }
        }

    private suspend fun fromCache(): Result<List<Banner>> = local.getAllBanners()

}