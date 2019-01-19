package br.com.cemobile.data.remote.features.product

import br.com.cemobile.data.local.errors.RemoteDataNotFoundException
import br.com.cemobile.data.remote.webservices.LodjinhaWebServices
import br.com.cemobile.domain.model.Product
import br.com.cemobile.domain.model.Result

class ProductRemoteDataSourceImpl(private val services: LodjinhaWebServices) : ProductRemoteDataSource {

    override suspend fun getBestSellingProducts(): Result<List<Product>> {
        val productsResponse = services.getBestSellingProducts().await()
        return Result.Success(productsResponse.data)
    }

    override suspend fun getAllProducts(offset: Int, limit: Int, categoryId: Long): Result<List<Product>> {
        val productsResponse = services.getProducts(offset, limit, categoryId).await()
        return Result.Success(productsResponse.data)
    }

    override suspend fun getProductById(id: Long): Result<Product> {
        val product = services.getProduct(id).await()
        return Result.Success(product)
    }

    override suspend fun reserve(productId: Long): Result<Boolean> {
        val result = services.reserve(productId).await()
        return if (result.value == RESERVATION_SUCCESS) {
            Result.Success(true)
        } else {
            Result.Error(RemoteDataNotFoundException())
        }
    }

    private companion object {
        const val RESERVATION_SUCCESS = "success"
    }
}