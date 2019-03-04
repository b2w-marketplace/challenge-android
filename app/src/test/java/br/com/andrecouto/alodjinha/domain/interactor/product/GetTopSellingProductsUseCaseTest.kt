package br.com.andrecouto.alodjinha.domain.interactor.product

import br.com.andrecouto.alodjinha.domain.factory.product.ProductFactory
import br.com.andrecouto.alodjinha.domain.mapper.DomainErrorUtil
import br.com.andrecouto.alodjinha.domain.model.lodjinha.Product
import br.com.andrecouto.alodjinha.domain.repository.ProductRepository
import br.com.andrecouto.alodjinha.domain.usecase.product.GetTopSellingProducts
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetTopSellingProductsUseCaseTest {

    private lateinit var getTopSellingProductsUseCase : GetTopSellingProducts
    @Mock
    lateinit var productRepository: ProductRepository
    @Mock
    lateinit var domainErrorUtil: DomainErrorUtil

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getTopSellingProductsUseCase = GetTopSellingProducts(domainErrorUtil, productRepository)
    }

    @Test
    fun getTopSellingProductsCompletes() {
        stubProducts(Single.just(ProductFactory.makeProductList(3)))
        val testSingle = getTopSellingProductsUseCase.buildUseCaseObservable().test()
        testSingle.assertComplete()
    }

    @Test
    fun getTopSellingProductsReturnData() {
        val products = ProductFactory.makeProductList(3)
        stubProducts(Single.just(products))
        val testSingle = getTopSellingProductsUseCase.buildUseCaseObservable().test()
        testSingle.assertValue(products)
    }

    private fun stubProducts(observable: Single<List<Product>>) {
        whenever(productRepository.getTopSellingProducts())
                .thenReturn(observable)
    }
}