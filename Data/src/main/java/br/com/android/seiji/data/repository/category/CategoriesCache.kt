package br.com.android.seiji.data.repository.category

import br.com.android.seiji.data.model.CategoryEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface CategoriesCache {

    fun getCategories(): Flowable<List<CategoryEntity>>
    fun saveCategories(categories: List<CategoryEntity>): Completable

    fun clearCategories(): Completable
    fun areCategoriesCached(): Single<Boolean>
    fun setLastCacheTime(lastCache: Long): Completable
    fun isCacheExpired(): Single<Boolean>
}