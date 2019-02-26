package br.com.andrecouto.alodjinha.data.source.remote.category

import br.com.andrecouto.alodjinha.domain.model.lodjinha.Category
import io.reactivex.Single

interface CategoryRemoteDataSource {
    fun getCategories() : Single<List<Category>>
}