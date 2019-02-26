package br.com.andrecouto.alodjinha.data.source.remote.repository

import br.com.andrecouto.alodjinha.data.source.remote.category.CategoryRemoteDataSource
import br.com.andrecouto.alodjinha.domain.model.lodjinha.Category
import br.com.andrecouto.alodjinha.domain.repository.CategoryRepository
import io.reactivex.Single
import javax.inject.Inject

//TODO Local Data Source
class CategoryRepositoryImpl @Inject constructor(
    var categoriaRemoteDataSource: CategoryRemoteDataSource
): CategoryRepository {


    override fun getCategories(): Single<List<Category>> {
        return categoriaRemoteDataSource.getCategories()
    }
}