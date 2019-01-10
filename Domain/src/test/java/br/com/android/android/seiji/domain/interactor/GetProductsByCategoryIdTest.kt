package br.com.android.android.seiji.domain.interactor

import br.com.android.seiji.domain.executor.PostExecutionThread
import br.com.android.seiji.domain.interactor.product.GetProductsByCategoryId
import br.com.android.seiji.domain.model.Product
import br.com.android.seiji.domain.repository.ProductRepository
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import test.DataFactory
import test.ProductFactory

class GetProductsByCategoryIdTest {

    private lateinit var getProductsByCategoryId: GetProductsByCategoryId

    @Mock
    lateinit var productsRepository: ProductRepository

    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getProductsByCategoryId = GetProductsByCategoryId(productsRepository, postExecutionThread)
    }

    @Test
    fun getProductsByIdCompletes() {
        stubProductsById(Observable.just(ProductFactory.makeProductList(5)))
        val testObserver = getProductsByCategoryId.buildUseCaseObservable(
            GetProductsByCategoryId.Params.forProduct(DataFactory.randomInt(), DataFactory.randomInt(), DataFactory.randomInt())
        ).test()
        testObserver.assertComplete()
    }

    @Test
    fun getProductsByIdReturnsData() {
        val productsList = ProductFactory.makeProductList(5)
        stubProductsById(Observable.just(productsList))
        val testObserver = getProductsByCategoryId.buildUseCaseObservable(
            GetProductsByCategoryId.Params.forProduct(DataFactory.randomInt(), DataFactory.randomInt(), DataFactory.randomInt())
        ).test()
        testObserver.assertValue(productsList)
    }

    @Test(expected = IllegalArgumentException::class)
    fun getProductsByIdThrowsException() {
        getProductsByCategoryId.buildUseCaseObservable().test()
    }

    private fun stubProductsById(observable: Observable<List<Product>>) {
        whenever(productsRepository.getProductsByCategoryId(any(), any(), any()))
            .thenReturn(observable)
    }
}