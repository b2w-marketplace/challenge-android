package br.com.cemobile.domain.features.product

import br.com.cemobile.domain.FetchStrategy
import br.com.cemobile.domain.model.Result
import br.com.cemobile.domain.repository.ProductRepository

class ReserveProductImpl(private val repository: ProductRepository) : ReserveProduct {

    override suspend fun reserve(productId: Long, strategy: FetchStrategy): Result<Boolean> =
        repository.reserve(productId)

}