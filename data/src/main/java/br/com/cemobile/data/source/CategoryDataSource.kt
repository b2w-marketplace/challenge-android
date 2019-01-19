package br.com.cemobile.data.source

import br.com.cemobile.domain.model.Category

interface CategoryDataSource {

    suspend fun getCategories(): Result<List<Category>>

    fun save(categories: List<Category>)

    fun deleteAll()

}