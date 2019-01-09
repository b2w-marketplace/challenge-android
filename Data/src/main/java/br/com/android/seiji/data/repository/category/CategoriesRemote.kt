package br.com.android.seiji.data.repository.category

import br.com.android.seiji.data.model.CategoryEntity
import io.reactivex.Flowable

interface CategoriesRemote {
    fun getCategories(): Flowable<List<CategoryEntity>>
}