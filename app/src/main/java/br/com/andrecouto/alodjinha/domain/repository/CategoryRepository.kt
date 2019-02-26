package br.com.andrecouto.alodjinha.domain.repository

import br.com.andrecouto.alodjinha.domain.model.lodjinha.Category
import io.reactivex.Single

interface CategoryRepository {
    fun getCategories() : Single<List<Category>>
}