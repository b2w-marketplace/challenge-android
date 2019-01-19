package br.com.cemobile.data.local.features.category

import br.com.cemobile.domain.model.Category
import br.com.cemobile.domain.model.Result

interface CategoryLocalDataSource {

    suspend fun getCategories(): Result<List<Category>>

    suspend fun save(categories: List<Category>)

    suspend fun deleteAll()

}