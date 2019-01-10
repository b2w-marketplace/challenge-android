package br.com.android.seiji.data.store.categories

import br.com.android.seiji.data.model.CategoryEntity
import br.com.android.seiji.data.repository.category.CategoriesCache
import br.com.android.seiji.data.store.category.CategoriesCacheDataStore
import br.com.android.seiji.data.test.factory.CategoryFactory
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.Flowable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CategoriesCacheDataStoreTest {

    private val cache = mock<CategoriesCache>()
    private val store = CategoriesCacheDataStore(cache)

    @Test
    fun getCategoriesCompletes() {
        stubGetCategories(Flowable.just(listOf(CategoryFactory.makeCategoryEntity())))

        val testObserver = store.getCategories().test()
        testObserver.assertComplete()
    }

    @Test
    fun getCategoriesReturnsData() {
        val data = listOf(CategoryFactory.makeCategoryEntity())
        stubGetCategories(Flowable.just(data))

        val testObserver = store.getCategories().test()
        testObserver.assertValue(data)
    }

    @Test
    fun getCategoriesCallsCacheSource() {
        stubGetCategories(Flowable.just(listOf(CategoryFactory.makeCategoryEntity())))

        store.getCategories().test()
        verify(cache).getCategories()
    }

    @Test
    fun saveCategoriesCompletes() {
        stubSaveCategories(Completable.complete())
        stubSetLastCacheTime(Completable.complete())

        val testObserver = store.saveCategories(listOf(CategoryFactory.makeCategoryEntity())).test()
        testObserver.assertComplete()
    }

    @Test
    fun saveCategoriesCallsCacheStore() {
        val data = listOf(CategoryFactory.makeCategoryEntity())

        stubSaveCategories(Completable.complete())
        stubSetLastCacheTime(Completable.complete())

        store.saveCategories(data).test()
        verify(cache).saveCategories(data)
    }

    @Test
    fun clearCategoriesCompletes() {
        stubClearCategories(Completable.complete())

        val testObserver = store.clearCategories().test()
        testObserver.assertComplete()
    }

    @Test
    fun clearCategoriesCallsCacheStore() {
        stubClearCategories(Completable.complete())

        store.clearCategories().test()
        verify(cache).clearCategories()
    }

    private fun stubGetCategories(observable: Flowable<List<CategoryEntity>>) {
        whenever(cache.getCategories())
            .thenReturn(observable)
    }

    private fun stubSaveCategories(completable: Completable) {
        whenever(cache.saveCategories(any()))
            .thenReturn(completable)
    }

    private fun stubSetLastCacheTime(completable: Completable) {
        whenever(cache.setLastCacheTime(any()))
            .thenReturn(completable)
    }

    private fun stubClearCategories(completable: Completable) {
        whenever(cache.clearCategories())
            .thenReturn(completable)
    }
}