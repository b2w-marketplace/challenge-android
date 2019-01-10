package br.com.android.seiji.data.store.products

import br.com.android.seiji.data.model.ProductEntity
import br.com.android.seiji.data.repository.product.ProductsCache
import br.com.android.seiji.data.store.product.ProductsCacheDataStore
import br.com.android.seiji.data.test.factory.DataFactory
import br.com.android.seiji.data.test.factory.ProductFactory
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.Flowable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ProductsCacheDataStoreTest {

    private val cache = mock<ProductsCache>()
    private val store = ProductsCacheDataStore(cache)

    @Test
    fun getBestSellerProductsCompletes() {
        stubBestSellerProductsCache(Flowable.just(listOf(ProductFactory.makeProductEntity())))

        val testObserver = store.getBestSellerProducts().test()
        testObserver.assertComplete()
    }

    @Test
    fun getBestSellerProductsReturnsData() {
        val data = listOf(ProductFactory.makeProductEntity())
        stubBestSellerProductsCache(Flowable.just(data))

        val testObserver = store.getBestSellerProducts().test()
        testObserver.assertValue(data)
    }

    @Test
    fun getBestSellerProductsCallsCacheSource() {
        stubBestSellerProductsCache(Flowable.just(listOf(ProductFactory.makeProductEntity())))

        store.getBestSellerProducts().test()
        verify(cache).getBestSellerProducts()
    }

    @Test
    fun saveBestSellerProductsCompletes() {
        stubSaveBestSellerProductsCache(Completable.complete())
        stubBestSellerProductsSetLastCacheTime(Completable.complete())

        val testObserver = store.saveBestSellerProducts(
            listOf(ProductFactory.makeProductEntity())
        ).test()
        testObserver.assertComplete()
    }

    @Test
    fun saveBestSellerProductsCallsCacheStore() {
        val data = listOf(ProductFactory.makeProductEntity())

        stubSaveBestSellerProductsCache(Completable.complete())
        stubBestSellerProductsSetLastCacheTime(Completable.complete())

        store.saveBestSellerProducts(data).test()
        verify(cache).saveBestSellerProducts(data)
    }

    @Test
    fun clearBestSellerProductsCompletes() {
        stubBestSellerProductsClearBanners(Completable.complete())

        val testObserver = store.clearBestSellerProducts().test()
        testObserver.assertComplete()
    }

    @Test
    fun clearBestSellerProductsCallsCacheStore() {
        stubBestSellerProductsClearBanners(Completable.complete())

        store.clearBestSellerProducts().test()
        verify(cache).clearBestSellerProducts()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun getProductsByCategoryIdThrowsException() {
        store.getProductsByCategoryId(
            DataFactory.randomInt(), DataFactory.randomInt(), DataFactory.randomInt()
        ).test()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun postProductReservationThrowsException() {
        store.postProductReservation(DataFactory.randomInt()).test()
    }

    private fun stubBestSellerProductsCache(observable: Flowable<List<ProductEntity>>) {
        whenever(cache.getBestSellerProducts())
            .thenReturn(observable)
    }

    private fun stubSaveBestSellerProductsCache(completable: Completable) {
        whenever(cache.saveBestSellerProducts(any()))
            .thenReturn(completable)
    }

    private fun stubBestSellerProductsSetLastCacheTime(completable: Completable) {
        whenever(cache.setLastProductsCacheTime(any()))
            .thenReturn(completable)
    }

    private fun stubBestSellerProductsClearBanners(completable: Completable) {
        whenever(cache.clearBestSellerProducts())
            .thenReturn(completable)
    }
}