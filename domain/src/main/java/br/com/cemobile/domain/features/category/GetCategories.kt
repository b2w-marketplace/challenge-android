package br.com.cemobile.domain.features.category

import br.com.cemobile.domain.FetchStrategy
import br.com.cemobile.domain.model.Category
import br.com.cemobile.domain.model.Result

interface GetCategories {

    suspend fun getCategories(strategy: FetchStrategy): Result<List<Category>>

}