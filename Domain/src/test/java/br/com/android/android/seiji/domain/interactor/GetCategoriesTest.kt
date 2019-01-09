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
import test.CategoriaFactory

class GetCategoriesTest {

    private lateinit var getCategories: GetCategories

    @Mock
    lateinit var categoryRepository: CategoryRepository

    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getCategories =
                GetCategories(categoryRepository, postExecutionThread)
    }

    @Test
    fun getCategoriasCompletes() {
        stubGetCategorias(Observable.just(CategoriaFactory.makeCategoriaList(5)))
        val testObserver = getCategories.buildUseCaseObservable().test()
        testObserver.assertComplete()
    }

    @Test
    fun getCategoriasReturnsData() {
        val categoriasList = CategoriaFactory.makeCategoriaList(5)
        stubGetCategorias(Observable.just(categoriasList))
        val testObserver = getCategories.buildUseCaseObservable().test()
        testObserver.assertValue(categoriasList)
    }

    private fun stubGetCategorias(observable: Observable<List<Category>>) {
        whenever(categoryRepository.getCategories())
            .thenReturn(observable)
    }
}