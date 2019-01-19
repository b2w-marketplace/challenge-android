package br.com.cemobile.domain.features.product

import br.com.cemobile.domain.FetchStrategy
import br.com.cemobile.domain.model.Product
import br.com.cemobile.domain.model.Result
import br.com.cemobile.domain.repository.ProductRepository

class GetBestSellingProductsImpl(private val repository: ProductRepository): GetBestSellingProducts {

    override suspend fun getBestSellingProducts(strategy: FetchStrategy): Result<List<Product>> =
        repository.getBestSellingProducts(strategy)

}