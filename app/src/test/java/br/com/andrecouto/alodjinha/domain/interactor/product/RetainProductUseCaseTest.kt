package br.com.andrecouto.alodjinha.domain.interactor.product

import br.com.andrecouto.alodjinha.DataFactory
import br.com.andrecouto.alodjinha.domain.mapper.DomainErrorUtil
import br.com.andrecouto.alodjinha.domain.repository.ProductRepository
import br.com.andrecouto.alodjinha.domain.usecase.product.RetainProduct
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class RetainProductUseCaseTest {

    private lateinit var retainProductUseCase: RetainProduct
    @Mock
    lateinit var productRepository: ProductRepository
    @Mock
    lateinit var domainErrorUtil: DomainErrorUtil

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        retainProductUseCase = RetainProduct(domainErrorUtil, productRepository)
    }

    @Test
    fun retainProductCompletes() {
        stubRetain(Completable.complete())
        val testObserver = retainProductUseCase.buildUseCaseObservable(
                RetainProduct.Params.forProject(DataFactory.randomInt())
        ).test()
        testObserver.assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun retainProductThrowsException() {
        retainProductUseCase.buildUseCaseObservable().test()
    }

    private fun stubRetain(observable: Completable) {
        whenever(productRepository.retainProduct(any()))
                .thenReturn(observable)
    }
}