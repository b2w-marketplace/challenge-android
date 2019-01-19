package br.com.cemobile.data.remote.features.product

import br.com.cemobile.domain.model.Product
import br.com.cemobile.domain.model.Result

interface ProductRemoteDataSource {

    suspend fun getBestSellingProducts(): Result<List<Product>>

    suspend fun getAllProducts(offset: Int, limit: Int, categoryId: Long): Result<List<Product>>

    suspend fun getProductById(id: Long): Result<Product>

    suspend fun reserve(productId: Long): Result<Boolean>

}