package br.com.cemobile.domain.features.product

import br.com.cemobile.domain.FetchStrategy
import br.com.cemobile.domain.model.Product
import br.com.cemobile.domain.model.Result

interface GetBestSellingProducts {

    suspend fun getBestSellingProducts(strategy: FetchStrategy): Result<List<Product>>

}