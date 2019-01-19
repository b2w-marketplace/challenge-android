package br.com.cemobile.data.repository

import br.com.cemobile.data.local.errors.RemoteDataNotFoundException
import br.com.cemobile.data.local.features.product.ProductLocalDataSource
import br.com.cemobile.data.remote.features.product.ProductRemoteDataSource
import br.com.cemobile.domain.FetchStrategy
import br.com.cemobile.domain.FetchStrategy.ForceUpdate
import br.com.cemobile.domain.FetchStrategy.FromPrevious
import br.com.cemobile.domain.model.Product
import br.com.cemobile.domain.model.Result
import br.com.cemobile.domain.repository.ProductRepository
import br.com.cemobile.lodjinha.ui.base.errors.NetworkErrorHandler

class ProductRepositoryImpl(
    private val remote: ProductRemoteDataSource,
    private val local: ProductLocalDataSource
) : ProductRepository {

    override suspend fun getBestSellingProducts(strategy: FetchStrategy): Result<List<Product>> = when (strategy) {
        is ForceUpdate -> bestSellingFromRemoteThenCache()
        is FromPrevious -> bestSellingFromCache()
    }

    override suspend fun getAllProducts(
        offset: Int,
        limit: Int,
        categoryId: Long,
        strategy: FetchStrategy
    ): Result<List<Product>> = when (strategy) {
        is ForceUpdate -> productsFromRemoteThenCache(offset, limit, categoryId)
        is FromPrevious -> productsFromCache(offset, limit, categoryId)
    }

    override suspend fun getProductById(id: Long, strategy: FetchStrategy): Result<Product> = when (strategy) {
        is ForceUpdate -> productByIdFromRemoteThenCache(id)
        is FromPrevious -> productByIdFromCache(id)
    }

    override suspend fun reserve(productId: Long): Result<Boolean> = remote.reserve(productId)

    override suspend fun deleteAll() = local.deleteAll()

    override suspend fun save(product: Product) = local.save(product)

    private suspend fun productsFromRemoteThenCache(offset: Int, limit: Int, categoryId: Long): Result<List<Product>> =
        try {
            val result = remote.getAllProducts(offset, limit, categoryId)
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

    private suspend fun productsFromCache(offset: Int, limit: Int, categoryId: Long): Result<List<Product>> =
        local.getAllProducts(offset, limit, categoryId)


    private suspend fun bestSellingFromRemoteThenCache(): Result<List<Product>> =
        try {
            val result = remote.getBestSellingProducts()
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

    private suspend fun bestSellingFromCache(): Result<List<Product>> =
        local.getBestSellingProducts()

    private suspend fun productByIdFromRemoteThenCache(id: Long): Result<Product> =
        try {
            val result = remote.getProductById(id)
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

    private suspend fun productByIdFromCache(id: Long): Result<Product> = local.getProductById(id)

}