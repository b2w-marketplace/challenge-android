package br.com.android.seiji.data.store.products

import br.com.android.seiji.data.model.ProductEntity
import br.com.android.seiji.data.repository.product.ProductsRemote
import br.com.android.seiji.data.store.product.ProductsRemoteDataStore
import br.com.android.seiji.data.test.factory.DataFactory
import br.com.android.seiji.data.test.factory.ProductFactory
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.Flowable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ProductsRemoteDataStoreTest {

    private val remote = mock<ProductsRemote>()
    private val store = ProductsRemoteDataStore(remote)

    @Test
    fun getBestSellerProductsCompletes() {
        stubRemoteBestSellerProducts(Flowable.just(listOf(ProductFactory.makeProductEntity())))
        val testObserver = store.getBestSellerProducts().test()
        testObserver.assertComplete()
    }

    @Test
    fun getBestSellerProductsReturnsData() {
        val data = listOf(ProductFactory.makeProductEntity())
        stubRemoteBestSellerProducts(Flowable.just((data)))
        val testObserver = store.getBestSellerProducts().test()
        testObserver.assertValue(data)
    }

    @Test
    fun getProductsByCategoryIdCompletes() {
        stubRemoteProductsByCategoryId(Flowable.just(listOf(ProductFactory.makeProductEntity())))
        val testObserver = store.getProductsByCategoryId(
            DataFactory.randomInt(), DataFactory.randomInt(), DataFactory.randomInt()
        ).test()
        testObserver.assertComplete()
    }

    @Test
    fun getProductsByCategoryIdReturnsData() {
        val data = listOf(ProductFactory.makeProductEntity())
        stubRemoteProductsByCategoryId(Flowable.just(data))
        val testObserver = store.getProductsByCategoryId(
            DataFactory.randomInt(), DataFactory.randomInt(), DataFactory.randomInt()
        ).test()
        testObserver.assertValue(data)
    }

    @Test
    fun doProductReservationCompletes() {
        stubDoProductReservation(Completable.complete())
        val testObserver = store.postProductReservation(
            DataFactory.randomInt()
        ).test()
        testObserver.assertComplete()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun saveBestSellerProductsThrowsException() {
        store.saveBestSellerProducts(listOf()).test()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun clearBestSellerProductsThrowsException() {
        store.clearBestSellerProducts().test()
    }

    private fun stubRemoteBestSellerProducts(observable: Flowable<List<ProductEntity>>) {
        whenever(remote.getBestSellerProducts())
            .thenReturn(observable)
    }

    private fun stubRemoteProductsByCategoryId(observable: Flowable<List<ProductEntity>>) {
        whenever(remote.getProductsByCategoryId(any(), any(), any()))
            .thenReturn(observable)
    }

    private fun stubDoProductReservation(completable: Completable) {
        whenever(remote.postProductReservation(any()))
            .thenReturn(completable)
    }
}