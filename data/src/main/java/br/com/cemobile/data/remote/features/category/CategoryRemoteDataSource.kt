package br.com.cemobile.data.remote.features.category

import br.com.cemobile.domain.model.Category
import br.com.cemobile.domain.model.Result

interface CategoryRemoteDataSource {

    suspend fun getCategories(): Result<List<Category>>

}