package br.com.cemobile.domain.features.category

import br.com.cemobile.domain.FetchStrategy
import br.com.cemobile.domain.model.Category
import br.com.cemobile.domain.model.Result
import br.com.cemobile.domain.repository.CategoryRepository

class GetCategoriesImpl(private val repository: CategoryRepository): GetCategories {

    override suspend fun getCategories(strategy: FetchStrategy): Result<List<Category>> =
        repository.getCategories(strategy)

}