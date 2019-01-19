package br.com.cemobile.data.source

import br.com.cemobile.domain.model.Product

interface ProductDataSource {

    suspend fun getBestSellingProducts(): Result<List<Product>>

    suspend fun getAllProducts(offset: Int, limit: Int, categoryId: Long): Result<List<Product>>

    suspend fun getProductById(id: Long): Result<Product>

    suspend fun reserve(productId: Long): Result<Boolean>

    fun save(products: List<Product>)

    fun save(product: Product)

    fun deleteAll()

}