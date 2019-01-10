package br.com.android.seiji.data.store.category

import br.com.android.seiji.data.model.CategoryEntity
import br.com.android.seiji.data.repository.category.CategoriesDataStore
import br.com.android.seiji.data.repository.category.CategoriesRemote
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class CategoriesRemoteDataStore @Inject constructor(
    private val categoriesRemote: CategoriesRemote
) : CategoriesDataStore {

    override fun getCategories(): Flowable<List<CategoryEntity>> {
        return categoriesRemote.getCategories()
    }

    override fun saveCategories(categories: List<CategoryEntity>): Completable {
        throw UnsupportedOperationException("Save Categories isn't supported here...")
    }

    override fun clearCategories(): Completable {
        throw UnsupportedOperationException("Clear Categories isn't supported here...")
    }
}