package br.com.cemobile.data.remote.features.category

import br.com.cemobile.data.remote.models.CategoriesResponse
import br.com.cemobile.data.remote.webservices.LodjinhaWebServices
import br.com.cemobile.domain.model.Category
import br.com.cemobile.domain.model.Result

class CategoryRemoteDataSourceImpl(private val services: LodjinhaWebServices) : CategoryRemoteDataSource {

    override suspend fun getCategories(): Result<List<Category>> {
        val bannerResponse: CategoriesResponse = services.getCategories().await()
        return Result.Success(bannerResponse.data)
    }

}