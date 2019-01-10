package br.com.android.seiji.remote

import br.com.android.seiji.data.model.ProductEntity
import br.com.android.seiji.remote.mapper.ProductsResponseModelMapper
import br.com.android.seiji.remote.model.ProductModel
import br.com.android.seiji.remote.model.ProductResponseModel
import br.com.android.seiji.remote.service.LodjinhaService
import br.com.android.seiji.remote.test.factory.DataFactory
import br.com.android.seiji.remote.test.factory.ProductFactory
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
class ProductsRemoteImplTest {

    private val mapper = mock<ProductsResponseModelMapper>()
    private val service = mock<LodjinhaService>()
    private val remote = ProductsRemoteImpl(service, mapper)

    @Test
    fun getBestSellerProductsCompletes() {
        stubGetBestSellerProducts(Flowable.just(ProductFactory.makeProductResponse()))
        stubProductsResponseModelMapperMapFromModel(
            ProductFactory.makeProductModel(),
            ProductFactory.makeProductEntity()
        )

        val testObserver = remote.getBestSellerProducts().test()
        testObserver.assertComplete()
    }

    @Test
    fun getBestSellerProductsCallsServer() {
        stubGetBestSellerProducts(Flowable.just(ProductFactory.makeProductResponse()))
        stubProductsResponseModelMapperMapFromModel(
            ProductFactory.makeProductModel(),
            ProductFactory.makeProductEntity()
        )

        remote.getBestSellerProducts().test()
        verify(service).getBestSellerProducts()
    }

    @Test
    fun getBestSellerProductsReturnsData() {
        val response = ProductFactory.makeProductResponse()
        stubGetBestSellerProducts(Flowable.just(response))

        val entities = mutableListOf<ProductEntity>()
        response.data.forEach {
            val entity = ProductFactory.makeProductEntity()
            entities.add(entity)
            stubProductsResponseModelMapperMapFromModel(it, entity)
        }

        val testObserver = remote.getBestSellerProducts().test()
        testObserver.assertValue(entities)
    }

    //getProductsByCategoryId

    @Test
    fun getProductsByCategoryIdCompletes() {
        stubProductsByCategoryId(Flowable.just(ProductFactory.makeProductResponse()))
        stubProductsResponseModelMapperMapFromModel(
            ProductFactory.makeProductModel(),
            ProductFactory.makeProductEntity()
        )

        val testObserver = remote.getProductsByCategoryId(
            DataFactory.randomInt(), DataFactory.randomInt(), DataFactory.randomInt()
        ).test()
        testObserver.assertComplete()
    }

    @Test
    fun getProductsByCategoryIdCallsServer() {
        stubProductsByCategoryId(Flowable.just(ProductFactory.makeProductResponse()))
        stubProductsResponseModelMapperMapFromModel(
            ProductFactory.makeProductModel(),
            ProductFactory.makeProductEntity()
        )

        val categoryId = DataFactory.randomInt()
        val offset = DataFactory.randomInt()
        val limit = DataFactory.randomInt()

        remote.getProductsByCategoryId(categoryId, offset, limit).test()
        verify(service).getProductsByCategoryId(categoryId, offset, limit)
    }

    @Test
    fun getProductByCategoryIdReturnsData() {
        val response = ProductFactory.makeProductResponse()
        stubProductsByCategoryId(Flowable.just(response))

        val entities = mutableListOf<ProductEntity>()
        response.data.forEach {
            val entity = ProductFactory.makeProductEntity()
            entities.add(entity)
            stubProductsResponseModelMapperMapFromModel(it, entity)
        }

        val testObserver = remote.getProductsByCategoryId(
            DataFactory.randomInt(), DataFactory.randomInt(), DataFactory.randomInt()
        ).test()
        testObserver.assertValue(entities)
    }

    @Test
    fun postProductReservationCompletes() {
        stubDoProductReservation(Completable.complete())
        val testObserver = remote.postProductReservation(
            DataFactory.randomInt()
        ).test()
        testObserver.assertComplete()
    }

    @Test
    fun postProductReservationCallsServer() {
        stubDoProductReservation(Completable.complete())

        val productId = DataFactory.randomInt()

        remote.postProductReservation(productId).test()
        verify(service).postProductReservation(productId)
    }

    private fun stubGetBestSellerProducts(observable: Flowable<ProductResponseModel>) {
        whenever(service.getBestSellerProducts())
            .thenReturn(observable)
    }

    private fun stubProductsByCategoryId(observable: Flowable<ProductResponseModel>) {
        whenever(service.getProductsByCategoryId(any(), any(), any()))
            .thenReturn(observable)
    }

    private fun stubDoProductReservation(completable: Completable) {
        whenever(service.postProductReservation(any()))
            .thenReturn(completable)
    }

    private fun stubProductsResponseModelMapperMapFromModel(
        model: ProductModel,
        entity: ProductEntity
    ) {
        whenever(mapper.mapFromModel(model))
            .thenReturn(entity)
    }
}