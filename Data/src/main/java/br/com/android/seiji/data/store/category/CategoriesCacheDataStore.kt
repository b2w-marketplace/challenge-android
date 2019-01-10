package br.com.android.seiji.data.store.category

import br.com.android.seiji.data.model.CategoryEntity
import br.com.android.seiji.data.repository.category.CategoriesCache
import br.com.android.seiji.data.repository.category.CategoriesDataStore
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class CategoriesCacheDataStore @Inject constructor(
    private val categoriesCache: CategoriesCache
) : CategoriesDataStore {

    override fun getCategories(): Flowable<List<CategoryEntity>> {
        return categoriesCache.getCategories()
    }

    override fun saveCategories(categories: List<CategoryEntity>): Completable {
        return categoriesCache.saveCategories(categories)
            .andThen(categoriesCache.setLastCategoriesCacheTime(System.currentTimeMillis()))
    }

    override fun clearCategories(): Completable {
        return categoriesCache.clearCategories()
    }
}