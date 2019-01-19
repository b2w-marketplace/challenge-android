package br.com.cemobile.data.local.features.product

import br.com.cemobile.domain.model.Product
import br.com.cemobile.domain.model.Result

interface ProductLocalDataSource {

    suspend fun getBestSellingProducts(): Result<List<Product>>

    suspend fun getAllProducts(offset: Int, limit: Int, categoryId: Long): Result<List<Product>>

    suspend fun getProductById(id: Long): Result<Product>

    suspend fun save(products: List<Product>)

    suspend fun save(product: Product)

    suspend fun deleteAll()

}