package br.com.android.seiji.data.store.categories

import br.com.android.seiji.data.model.CategoryEntity
import br.com.android.seiji.data.repository.category.CategoriesRemote
import br.com.android.seiji.data.store.category.CategoriesRemoteDataStore
import br.com.android.seiji.data.test.factory.CategoryFactory
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Flowable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CategoriesRemoteDataStoreTest {

    private val remote = mock<CategoriesRemote>()
    private val store = CategoriesRemoteDataStore(remote)

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

    @Test(expected = UnsupportedOperationException::class)
    fun clearCategoriesThrowsException() {
        store.clearCategories().test()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun saveCategoriesThrowsException() {
        store.saveCategories(listOf(CategoryFactory.makeCategoryEntity())).test()
    }

    private fun stubGetCategories(observable: Flowable<List<CategoryEntity>>) {
        whenever(store.getCategories())
            .thenReturn(observable)
    }
}