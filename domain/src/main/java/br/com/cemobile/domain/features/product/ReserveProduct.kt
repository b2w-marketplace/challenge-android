package br.com.cemobile.domain.features.product

import br.com.cemobile.domain.FetchStrategy
import br.com.cemobile.domain.model.Result

interface ReserveProduct {

    suspend fun reserve(productId: Long, strategy: FetchStrategy): Result<Boolean>

}