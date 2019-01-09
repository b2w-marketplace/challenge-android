package br.com.android.seiji.data.repository

import br.com.android.seiji.data.mapper.CategoryMapper
import br.com.android.seiji.data.model.CategoryEntity
import br.com.android.seiji.data.repository.category.CategoriesCache
import br.com.android.seiji.data.repository.category.CategoriesDataStore
import br.com.android.seiji.data.store.category.CategoriesDataRepository
import br.com.android.seiji.data.store.category.CategoriesDataStoreFactory
import br.com.android.seiji.data.test.factory.CategoryFactory
import br.com.android.seiji.domain.model.Category
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CategoriesDataRepositoryTest {

    private val mapper = mock<CategoryMapper>()
    private val factory = mock<CategoriesDataStoreFactory>()
    private val store = mock<CategoriesDataStore>()
    private val cache = mock<CategoriesCache>()
    private val repository = CategoriesDataRepository(mapper, cache, factory)

    @Before
    fun setup() {
        stubFactoryGetDataStore()
        stubFactoryGetCacheDataStore()
        stubIsCacheExpired(Single.just(false))
        stubAreCategoriesCached(Single.just(false))
        stubSaveCategories(Completable.complete())
    }

    @Test
    fun getBannersCompletes() {
        stubGetCategories(Flowable.just(listOf(CategoryFactory.makeCategoryEntity())))
        stubMapper(CategoryFactory.makeCategory(), CategoryFactory.makeCategoryEntity())

        val testObserver = repository.getCategories().test()
        testObserver.assertComplete()
    }

    @Test
    fun getBannersReturnsData() {
        val entity = CategoryFactory.makeCategoryEntity()
        val model = CategoryFactory.makeCategory()

        stubGetCategories(Flowable.just(listOf(entity)))
        stubMapper(model, entity)

        val testObserver = repository.getCategories().test()
        testObserver.assertValue(listOf(model))
    }

    private fun stubFactoryGetDataStore() {
        whenever(factory.getDataStore(any(), any()))
            .thenReturn(store)
    }

    private fun stubFactoryGetCacheDataStore() {
        whenever(factory.getCacheDataStore())
            .thenReturn(store)
    }

    private fun stubMapper(model: Category, entity: CategoryEntity) {
        whenever(mapper.mapFromEntity(entity))
            .thenReturn(model)
    }

    private fun stubGetCategories(observable: Flowable<List<CategoryEntity>>) {
        whenever(store.getCategories())
            .thenReturn(observable)
    }

    private fun stubIsCacheExpired(single: Single<Boolean>) {
        whenever(cache.isCategoriesCacheExpired())
            .thenReturn(single)
    }

    private fun stubAreCategoriesCached(single: Single<Boolean>) {
        whenever(cache.areCategoriesCached())
            .thenReturn(single)
    }

    private fun stubSaveCategories(completable: Completable) {
        whenever(store.saveCategories(any()))
            .thenReturn(completable)
    }
}