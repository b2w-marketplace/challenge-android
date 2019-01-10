package br.com.android.seiji.data.store.category

import br.com.android.seiji.data.mapper.CategoryMapper
import br.com.android.seiji.data.repository.category.CategoriesCache
import br.com.android.seiji.domain.model.Category
import br.com.android.seiji.domain.repository.CategoryRepository
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class CategoriesDataRepository @Inject constructor(
    private val mapper: CategoryMapper,
    private val cache: CategoriesCache,
    private val factory: CategoriesDataStoreFactory
) : CategoryRepository {

    override fun getCategories(): Observable<List<Category>> {
        return Observable.zip(cache.areCategoriesCached().toObservable(),
            cache.isCacheExpired().toObservable(),
            BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>> { areCached, isExpired ->
                Pair(areCached, isExpired)
            })
            .flatMap {
                factory.getDataStore(it.first, it.second)
                    .getCategories().toObservable().distinctUntilChanged()
            }
            .flatMap { categories ->
                factory.getCacheDataStore()
                    .saveCategories(categories)
                    .andThen(Observable.just(categories))
            }
            .map {
                it.map {
                    mapper.mapFromEntity(it)
                }
            }
    }
}