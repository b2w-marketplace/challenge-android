package br.com.andrecouto.alodjinha.data.repository

import br.com.andrecouto.alodjinha.data.source.remote.product.ProductRemoteDataSource
import br.com.andrecouto.alodjinha.data.source.remote.repository.ProductRepositoryImpl
import br.com.andrecouto.alodjinha.domain.factory.product.ProductFactory
import br.com.andrecouto.alodjinha.domain.model.lodjinha.Product
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.Single
import org.junit.Test

class ProductRemoteDataSourceImplTest {

    private val remoteDataSource = mock<ProductRemoteDataSource>()
    private val repository = ProductRepositoryImpl(remoteDataSource)

    @Test
    fun getTopSellingProductsCompletes() {
        stubTopSellingProducts(Single.just(ProductFactory.makeProductList(5)))
        val testSingle = repository.getTopSellingProducts().test()
        testSingle.assertComplete()
    }

    @Test
    fun getTopSellingProductsReturnData() {
        val produtos = ProductFactory.makeProductList(5)
        stubTopSellingProducts(Single.just(produtos))
        val testSingle = repository.getTopSellingProducts().test()
        testSingle.assertValue(produtos)
    }

    @Test
    fun getProductsCompletes() {
        stubProducts(Single.just(ProductFactory.makeProductList(5)))
        val testSingle = repository.getProducts(any()).test()
        testSingle.assertComplete()
    }

    @Test
    fun getProductsReturnData() {
        val produtos = ProductFactory.makeProductList(5)
        stubProducts(Single.just(produtos))
        val testSingle = repository.getProducts(any()).test()
        testSingle.assertValue(produtos)
    }

    @Test
    fun getProductDetailsCompletes() {
        stubProductDetails(Single.just(ProductFactory.makeProduct()))
        val testSingle = repository.getProduct(any()).test()
        testSingle.assertComplete()
    }

    @Test
    fun getProductDetailsReturnData() {
        val produto = ProductFactory.makeProduct()
        stubProductDetails(Single.just(produto))
        val testSingle = repository.getProduct(any()).test()
        testSingle.assertValue(produto)
    }

    @Test
    fun retainProductCompletes() {
        stubRetain(Completable.complete())
        val testObserver = repository.retainProduct(any()).test()
        testObserver.assertComplete()
    }

    private fun stubTopSellingProducts(observable: Single<List<Product>>) {
        whenever(remoteDataSource.getTopSellingProducts())
                .thenReturn(observable)
    }

    private fun stubProductDetails(observable: Single<Product>) {
        whenever(remoteDataSource.getProductDetailById(any()))
                .thenReturn(observable)
    }

    private fun stubProducts(observable: Single<List<Product>>) {
        whenever(remoteDataSource.getProductList(any()))
                .thenReturn(observable)
    }

    private fun stubRetain(observable: Completable) {
        whenever(remoteDataSource.retainProduct(any()))
                .thenReturn(observable)
    }
}