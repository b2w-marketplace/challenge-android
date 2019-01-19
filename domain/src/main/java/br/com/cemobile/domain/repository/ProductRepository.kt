package br.com.cemobile.domain.repository

import br.com.cemobile.domain.FetchStrategy
import br.com.cemobile.domain.model.Product
import br.com.cemobile.domain.model.Result

interface ProductRepository {

    suspend fun getBestSellingProducts(strategy: FetchStrategy): Result<List<Product>>

    suspend fun getAllProducts(offset: Int, limit: Int, categoryId: Long, strategy: FetchStrategy): Result<List<Product>>

    suspend fun getProductById(id: Long, strategy: FetchStrategy): Result<Product>

    suspend fun reserve(productId: Long): Result<Boolean>

    suspend fun deleteAll()

    suspend fun save(product: Product)

}