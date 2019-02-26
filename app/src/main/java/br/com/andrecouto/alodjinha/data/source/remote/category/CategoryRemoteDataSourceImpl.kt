package br.com.andrecouto.alodjinha.data.source.remote.category

import br.com.andrecouto.alodjinha.data.source.remote.service.Service
import br.com.andrecouto.alodjinha.domain.model.lodjinha.Category
import io.reactivex.Single
import javax.inject.Inject

class CategoryRemoteDataSourceImpl @Inject constructor(
        var service: Service
) : CategoryRemoteDataSource {


    override fun getCategories(): Single<List<Category>> {
        return service
                .getCategories()
                .map { it.data }
    }
}