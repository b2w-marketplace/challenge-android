package br.com.cemobile.domain.features.product

import br.com.cemobile.domain.FetchStrategy
import br.com.cemobile.domain.model.Product
import br.com.cemobile.domain.model.Result

interface GetProducts {

    suspend fun getProducts(
        offset: Int,
        limit: Int,
        categoryId: Long,
        strategy: FetchStrategy
    ): Result<List<Product>>

}