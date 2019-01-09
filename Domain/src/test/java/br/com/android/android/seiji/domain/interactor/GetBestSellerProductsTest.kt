package br.com.android.android.seiji.domain.interactor

import br.com.android.seiji.domain.executor.PostExecutionThread
import br.com.android.seiji.domain.interactor.product.GetBestSellerProducts
import br.com.android.seiji.domain.model.Product
import br.com.android.seiji.domain.repository.ProductRepository
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import test.ProductFactory

class GetBestSellerProductsTest {

    private lateinit var getBestSellerProducts: GetBestSellerProducts

    @Mock
    lateinit var productsRepository: ProductRepository

    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getBestSellerProducts = GetBestSellerProducts(productsRepository, postExecutionThread)
    }

    @Test
    fun getBestSellerProductsCompletes() {
        stubBestSellerProducts(Observable.just(ProductFactory.makeProductList(5)))
        val testObserver = getBestSellerProducts.buildUseCaseObservable().test()
        testObserver.assertComplete()
    }

    @Test
    fun getBestSellerProductsReturnsData() {
        val productsList = ProductFactory.makeProductList(5)
        stubBestSellerProducts(Observable.just(productsList))
        val testObserver = getBestSellerProducts.buildUseCaseObservable().test()
        testObserver.assertValue(productsList)
    }

    private fun stubBestSellerProducts(observable: Observable<List<Product>>) {
        whenever(productsRepository.getBestSellerProducts())
            .thenReturn(observable)
    }
}