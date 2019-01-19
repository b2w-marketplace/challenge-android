package br.com.cemobile.domain.features.product

import br.com.cemobile.domain.FetchStrategy
import br.com.cemobile.domain.model.Product
import br.com.cemobile.domain.model.Result
import br.com.cemobile.domain.repository.ProductRepository

class GetProductsImpl(private val repository: ProductRepository) : GetProducts {

    /**
     * Get product according to the specified parameters
     */
    override suspend fun getProducts(
        offset: Int,
        limit: Int,
        categoryId: Long,
        strategy: FetchStrategy
    ): Result<List<Product>> =
        repository.getAllProducts(offset, limit, categoryId, strategy)

}