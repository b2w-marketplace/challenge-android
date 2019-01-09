package br.com.android.android.seiji.domain.interactor

import br.com.android.seiji.domain.executor.PostExecutionThread
import br.com.android.seiji.domain.interactor.category.GetCategories
import br.com.android.seiji.domain.model.Category
import br.com.android.seiji.domain.repository.CategoryRepository
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import test.CategoryFactory

class GetCategoriesTest {

    private lateinit var getCategories: GetCategories

    @Mock
    lateinit var categoryRepository: CategoryRepository

    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getCategories = GetCategories(categoryRepository, postExecutionThread)
    }

    @Test
    fun getCategoriesCompletes() {
        stubGetCategories(Observable.just(CategoryFactory.makeCategoryList(5)))
        val testObserver = getCategories.buildUseCaseObservable().test()
        testObserver.assertComplete()
    }

    @Test
    fun getCategoriesReturnsData() {
        val categoriesList = CategoryFactory.makeCategoryList(5)
        stubGetCategories(Observable.just(categoriesList))
        val testObserver = getCategories.buildUseCaseObservable().test()
        testObserver.assertValue(categoriesList)
    }

    private fun stubGetCategories(observable: Observable<List<Category>>) {
        whenever(categoryRepository.getCategories())
            .thenReturn(observable)
    }
}