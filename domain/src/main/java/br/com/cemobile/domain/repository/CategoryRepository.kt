package br.com.cemobile.domain.repository

import br.com.cemobile.domain.FetchStrategy
import br.com.cemobile.domain.model.Category
import br.com.cemobile.domain.model.Result

interface CategoryRepository {

    suspend fun getCategories(strategy: FetchStrategy): Result<List<Category>>

}