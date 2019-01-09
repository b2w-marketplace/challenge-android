package br.com.android.seiji.data.store.category

import br.com.android.seiji.data.repository.category.CategoriesDataStore
import javax.inject.Inject

class CategoriesDataStoreFactory @Inject constructor(
    private val categoriesCacheDataStore: CategoriesCacheDataStore,
    private val categoriesRemoteDataStore: CategoriesRemoteDataStore
) {

    open fun getDataStore(isCached: Boolean, cacheExpired: Boolean): CategoriesDataStore {
        return if (isCached && !cacheExpired) {
            categoriesCacheDataStore
        } else {
            categoriesRemoteDataStore
        }
    }

    open fun getCacheDataStore(): CategoriesDataStore {
        return categoriesCacheDataStore
    }
}