package br.com.andrecouto.alodjinha.domain.interactor.product

import br.com.andrecouto.alodjinha.domain.factory.product.ProductFactory
import br.com.andrecouto.alodjinha.domain.mapper.DomainErrorUtil
import br.com.andrecouto.alodjinha.domain.model.lodjinha.Product
import br.com.andrecouto.alodjinha.domain.repository.ProductRepository
import br.com.andrecouto.alodjinha.domain.usecase.product.GetProducts
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetProductsUseCaseTest {

    private lateinit var getProductsUseCase : GetProducts
    @Mock
    lateinit var productRepository: ProductRepository
    @Mock
    lateinit var domainErrorUtil: DomainErrorUtil

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getProductsUseCase = GetProducts(domainErrorUtil, productRepository)
    }

    @Test
    fun getProductsCompletes() {
        stubProducts(Single.just(ProductFactory.makeProductList(3)))
        val testSingle = getProductsUseCase.buildUseCaseObservable(
                GetProducts.Params.forProject(any())).test()
        testSingle.assertComplete()
    }

    @Test
    fun getProductsReturnData() {
        val products = ProductFactory.makeProductList(3)
        stubProducts(Single.just(products))
        val testSingle = getProductsUseCase.buildUseCaseObservable(
                GetProducts.Params.forProject(any())).test()
        testSingle.assertValue(products)
    }

    @Test(expected = IllegalArgumentException::class)
    fun getProductsThrowsException() {
        getProductsUseCase.buildUseCaseObservable().test()
    }

    private fun stubProducts(observable: Single<List<Product>>) {
        whenever(productRepository.getProducts(any()))
                .thenReturn(observable)
    }
}