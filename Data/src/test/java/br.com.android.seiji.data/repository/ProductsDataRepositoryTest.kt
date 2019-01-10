package br.com.android.seiji.data.repository

import br.com.android.seiji.data.mapper.ProductMapper
import br.com.android.seiji.data.model.ProductEntity
import br.com.android.seiji.data.repository.product.ProductsCache
import br.com.android.seiji.data.repository.product.ProductsDataStore
import br.com.android.seiji.data.store.product.ProductsDataRepository
import br.com.android.seiji.data.store.product.ProductsDataStoreFactory
import br.com.android.seiji.data.test.factory.DataFactory
import br.com.android.seiji.data.test.factory.ProductFactory
import br.com.android.seiji.domain.model.Product
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ProductsDataRepositoryTest {

    private val mapper = mock<ProductMapper>()
    private val factory = mock<ProductsDataStoreFactory>()
    private val store = mock<ProductsDataStore>()
    private val cache = mock<ProductsCache>()
    private val repository = ProductsDataRepository(mapper, cache, factory)

    @Before
    fun setup() {
        stubFactoryGetDataStore()
        stubFactoryGetCacheDataStore()
        stubFactoryGetRemoteDataStore()
        stubIsCacheExpired(Single.just(false))
        stubAreCategoriesCached(Single.just(false))
        stubSaveBestSellerProducts(Completable.complete())
    }

    @Test
    fun getBestSellerProductsCompletes() {
        stubGetBestSellerProducts(Flowable.just(listOf(ProductFactory.makeProductEntity())))
        stubMapper(ProductFactory.makeProduct(), ProductFactory.makeProductEntity())

        val testObserver = repository.getBestSellerProducts().test()
        testObserver.assertComplete()
    }

    @Test
    fun getBestSellerProductsReturnsData() {
        val entity = ProductFactory.makeProductEntity()
        val model = ProductFactory.makeProduct()

        stubGetBestSellerProducts(Flowable.just(listOf(entity)))
        stubMapper(model, entity)

        val testObserver = repository.getBestSellerProducts().test()
        testObserver.assertValue(listOf(model))
    }

    @Test
    fun getProductsByIdCompletes() {
        stubGetProductsById(Flowable.just(listOf(ProductFactory.makeProductEntity())))
        stubMapper(ProductFactory.makeProduct(), any())

        val testObserver =
            repository.getProductsByCategoryId(
                DataFactory.randomInt(), DataFactory.randomInt(), DataFactory.randomInt()
            ).test()
        testObserver.assertComplete()
    }

    @Test
    fun getProductsByIdReturnsData() {
        val productEntity = ProductFactory.makeProductEntity()
        val product = ProductFactory.makeProduct()
        stubGetProductsById(Flowable.just(listOf(productEntity)))
        stubMapper(product, productEntity)

        val testObserver = repository.getProductsByCategoryId(
            DataFactory.randomInt(), DataFactory.randomInt(), DataFactory.randomInt()
        ).test()
        testObserver.assertValue(listOf(product))
    }

    @Test
    fun doProductReservationCompletes() {
        stubDoProductReservation(Completable.complete())
        val testObserver = repository.doProductReservation(
            DataFactory.randomInt()
        ).test()
        testObserver.assertComplete()
    }

    private fun stubFactoryGetDataStore() {
        whenever(factory.getDataStore(any(), any()))
            .thenReturn(store)
    }

    private fun stubFactoryGetCacheDataStore() {
        whenever(factory.getCacheDataStore())
            .thenReturn(store)
    }

    private fun stubFactoryGetRemoteDataStore() {
        whenever(factory.getRemoteDataStore())
            .thenReturn(store)
    }

    private fun stubMapper(model: Product, entity: ProductEntity) {
        whenever(mapper.mapFromEntity(entity))
            .thenReturn(model)
    }

    private fun stubGetBestSellerProducts(observable: Flowable<List<ProductEntity>>) {
        whenever(store.getBestSellerProducts())
            .thenReturn(observable)
    }

    private fun stubGetProductsById(observable: Flowable<List<ProductEntity>>) {
        whenever(store.getProductsByCategoryId(any(), any(), any()))
            .thenReturn(observable)
    }

    private fun stubDoProductReservation(completable: Completable) {
        whenever(store.postProductReservation(any()))
            .thenReturn(completable)
    }

    private fun stubIsCacheExpired(single: Single<Boolean>) {
        whenever(cache.isProductsCacheExpired())
            .thenReturn(single)
    }

    private fun stubAreCategoriesCached(single: Single<Boolean>) {
        whenever(cache.areProductsCached())
            .thenReturn(single)
    }

    private fun stubSaveBestSellerProducts(completable: Completable) {
        whenever(store.saveBestSellerProducts(any()))
            .thenReturn(completable)
    }
}