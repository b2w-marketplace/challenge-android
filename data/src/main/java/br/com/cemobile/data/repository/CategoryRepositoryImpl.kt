package br.com.cemobile.data.repository

import br.com.cemobile.data.local.errors.RemoteDataNotFoundException
import br.com.cemobile.data.local.features.category.CategoryLocalDataSource
import br.com.cemobile.data.remote.features.category.CategoryRemoteDataSource
import br.com.cemobile.domain.FetchStrategy
import br.com.cemobile.domain.FetchStrategy.ForceUpdate
import br.com.cemobile.domain.FetchStrategy.FromPrevious
import br.com.cemobile.domain.model.Category
import br.com.cemobile.domain.model.Result
import br.com.cemobile.domain.repository.CategoryRepository
import br.com.cemobile.lodjinha.ui.base.errors.NetworkErrorHandler

class CategoryRepositoryImpl(
    private val remote: CategoryRemoteDataSource,
    private val local: CategoryLocalDataSource
) : CategoryRepository {

    override suspend fun getCategories(strategy: FetchStrategy): Result<List<Category>> = when (strategy) {
        is ForceUpdate -> fromRemoteThenCache()
        is FromPrevious -> fromCache()
    }

    private suspend fun fromRemoteThenCache(): Result<List<Category>> =
        try {
            val result = remote.getCategories()
            when (result) {
                is Result.Success -> {
                    local.save(result.data)
                    result
                }
                is Result.Error -> Result.Error(RemoteDataNotFoundException())
                else -> Result.Error(RemoteDataNotFoundException())
            }
        } catch (error: Throwable) {
            if (NetworkErrorHandler.checkIfNetworkError(error)) {
                Result.Error(NetworkErrorHandler.mapToNetworkError(error))
            } else {
                Result.Error(error)
            }
        }

    private suspend fun fromCache(): Result<List<Category>> = local.getCategories()

}