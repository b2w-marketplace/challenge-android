package br.com.android.seiji.data.repository.category

import br.com.android.seiji.data.model.CategoryEntity
import io.reactivex.Completable
import io.reactivex.Flowable

interface CategoriesDataStore {

    fun getCategories(): Flowable<List<CategoryEntity>>

    fun saveCategories(categories: List<CategoryEntity>): Completable

    fun clearCategories(): Completable
}