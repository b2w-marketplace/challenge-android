package br.com.android.android.seiji.domain.interactor

import br.com.android.seiji.domain.executor.PostExecutionThread
import br.com.android.seiji.domain.interactor.browse.GetCategorias
import br.com.android.seiji.domain.model.Categoria
import br.com.android.seiji.domain.repository.CategoriasRepository
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import test.CategoriaFactory

class GetCategoriasTest {

    private lateinit var getCategorias: GetCategorias

    @Mock
    lateinit var categoriasRepository: CategoriasRepository

    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getCategorias = GetCategorias(categoriasRepository, postExecutionThread)
    }

    @Test
    fun getCategoriasCompletes() {
        stubGetCategorias(Observable.just(CategoriaFactory.makeCategoriaList(5)))
        val testObserver = getCategorias.buildUseCaseObservable().test()
        testObserver.assertComplete()
    }

    @Test
    fun getCategoriasReturnsData() {
        val categoriasList = CategoriaFactory.makeCategoriaList(5)
        stubGetCategorias(Observable.just(categoriasList))
        val testObserver = getCategorias.buildUseCaseObservable().test()
        testObserver.assertValue(categoriasList)
    }

    private fun stubGetCategorias(observable: Observable<List<Categoria>>) {
        whenever(categoriasRepository.getCategorias())
            .thenReturn(observable)
    }
}