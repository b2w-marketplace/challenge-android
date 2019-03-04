package br.com.andrecouto.alodjinha.domain.interactor.product

import br.com.andrecouto.alodjinha.domain.factory.product.ProductFactory
import br.com.andrecouto.alodjinha.domain.mapper.DomainErrorUtil
import br.com.andrecouto.alodjinha.domain.model.lodjinha.Product
import br.com.andrecouto.alodjinha.domain.repository.ProductRepository
import br.com.andrecouto.alodjinha.domain.usecase.product.GetProductDetails
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetProductDetailsUseCaseTest {

    private lateinit var getProductDetailsUseCase : GetProductDetails
    @Mock
    lateinit var productRepository: ProductRepository
    @Mock
    lateinit var domainErrorUtil: DomainErrorUtil

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getProductDetailsUseCase = GetProductDetails(domainErrorUtil, productRepository)
    }

    @Test
    fun getProductDetailsCompletes() {
        stubProducts(Single.just(ProductFactory.makeProduct()))
        val testSingle = getProductDetailsUseCase.buildUseCaseObservable(
                GetProductDetails.Params.forProject(any())).test()
        testSingle.assertComplete()
    }

    @Test
    fun getProductDetailsReturnData() {
        val product = ProductFactory.makeProduct()
        stubProducts(Single.just(product))
        val testSingle = getProductDetailsUseCase.buildUseCaseObservable(
                GetProductDetails.Params.forProject(any())).test()
        testSingle.assertValue(product)
    }

    @Test(expected = IllegalArgumentException::class)
    fun getProductDetailsThrowsException() {
        getProductDetailsUseCase.buildUseCaseObservable().test()
    }

    private fun stubProducts(observable: Single<Product>) {
        whenever(productRepository.getProduct(any()))
                .thenReturn(observable)
    }
}