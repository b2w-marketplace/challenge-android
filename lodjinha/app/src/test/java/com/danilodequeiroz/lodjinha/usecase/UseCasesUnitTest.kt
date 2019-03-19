package com.danilodequeiroz.lodjinha.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.danilodequeiroz.lodjinha.*
import com.danilodequeiroz.lodjinha.home.domain.HomeInteractor
import com.danilodequeiroz.lodjinha.productdetail.domain.ProductDetailInteractor
import com.danilodequeiroz.lodjinha.productlist.domain.ProductsListListInteractor
import com.danilodequeiroz.lodjinha.productlist.domain.ProductsListUseCase
import com.danilodequeiroz.webapi.LodjinhaRestRepository
import com.danilodequeiroz.webapi.model.banner.BannersPayload
import com.danilodequeiroz.webapi.model.category.ProductCategoriesPayload
import com.danilodequeiroz.webapi.model.product.BestSellingProductsPayload
import com.danilodequeiroz.webapi.model.product.Product
import com.danilodequeiroz.webapi.model.product.ProductsPayload
import common.mock
import common.whenever
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.*
import org.mockito.Mockito

/**
 * Created by Antoni Castejón
 * 28/01/2018.
 */
class UseCasesUnitTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    val lodjinhaRestRepository = mock<LodjinhaRestRepository>()

    val detailUseCase by lazy { ProductDetailInteractor(lodjinhaRestRepository) }
    val homeUseCase by lazy { HomeInteractor(lodjinhaRestRepository) }
    val productsUseCase by lazy { ProductsListListInteractor(lodjinhaRestRepository) }

    @Test
    fun testProductDetailUseCases_getProduct_Completed() {
        val product = Mockito.mock(Product::class.java)
        whenever(lodjinhaRestRepository.getProduct(anyInt()))
            .thenReturn(Single.just(product))

        detailUseCase.getProduct(0)
            .test()
            .assertComplete()
    }

    @Test
    fun testProductDetailUseCases_getProduct_Error() {
        val response = Throwable("Error response")
        whenever(lodjinhaRestRepository.getProduct(anyInt()))
                .thenReturn(Single.error(response))

        detailUseCase.getProduct(0)
                .test()
                .assertError(response)

    }

    @Test
    fun testProductDetailUseCases_getProduct_response() {
        val response = productPOJOmodel()
        whenever(lodjinhaRestRepository.getProduct(anyInt()))
                .thenReturn(Single.just(response))

        val expectedList = detailedProductViewModelFrom(productPOJOmodel())

        detailUseCase.getProduct(0)
                .test()
                .assertValue(expectedList)
    }

    @Test
    fun testProductDetailUseCases_getBanners_Completed() {
        val banner = Mockito.mock(BannersPayload::class.java)
        whenever(lodjinhaRestRepository.getBanners())
            .thenReturn(Single.just(banner))

        homeUseCase.getBanners()
            .test()
            .assertComplete()
    }

    @Test
    fun testProductDetailUseCases_getBanners_Error() {
        val response = Throwable("Error response")
        whenever(lodjinhaRestRepository.getBanners())
            .thenReturn(Single.error(response))

        homeUseCase.getBanners()
            .test()
            .assertError(response)

    }

    @Test
    fun testProductDetailUseCases_getBanners_response() {
        val response = bannersPOJOmodel()
        whenever(lodjinhaRestRepository.getBanners())
            .thenReturn(Single.just(response))

        val expectedList = bannersPOJOmodel().data?.mapNotNull { it?.let { it1 -> bannerViewModelFrom(it1) } }?.toMutableList()

        homeUseCase.getBanners()
            .test()
            .assertValue(expectedList)
    }


    @Test
    fun testProductDetailUseCases_getAllBestSellingProducts_Completed() {
        val bestSellingProductsPayload = Mockito.mock(BestSellingProductsPayload::class.java)
        whenever(lodjinhaRestRepository.getBestSellingProducts())
            .thenReturn(Single.just(bestSellingProductsPayload))

        homeUseCase.getAllBestSellingProducts()
            .test()
            .assertComplete()
    }

    @Test
    fun testProductDetailUseCases_getAllBestSellingProducts_Error() {
        val response = Throwable("Error response")
        whenever(lodjinhaRestRepository.getBestSellingProducts())
            .thenReturn(Single.error(response))

        homeUseCase.getAllBestSellingProducts()
            .test()
            .assertError(response)

    }

    @Test
    fun testProductDetailUseCases_getBestSellingProducts_response() {
        val response = bestSellingsPOJOmodel()
        whenever(lodjinhaRestRepository.getBestSellingProducts())
            .thenReturn(Single.just(response))

        val expectedList = bestSellingsPOJOmodel().data?.map {  it1 -> productViewModelFrom(it1)  }?.toMutableList()

        homeUseCase.getAllBestSellingProducts()
            .test()
            .assertValue(expectedList)
    }


    @Test
    fun testProductDetailUseCases_getProductCategories_Completed() {
        val productCategoriesPayload = Mockito.mock(ProductCategoriesPayload::class.java)
        whenever(lodjinhaRestRepository.getProductCategories())
            .thenReturn(Single.just(productCategoriesPayload))

        homeUseCase.getProductCategories()
            .test()
            .assertComplete()
    }

    @Test
    fun testProductDetailUseCases_getProductCategories_Error() {
        val response = Throwable("Error response")
        whenever(lodjinhaRestRepository.getProductCategories())
            .thenReturn(Single.error(response))

        homeUseCase.getProductCategories()
            .test()
            .assertError(response)

    }

    @Test
    fun testProductDetailUseCases_getProductCategories_response() {
        val response = categoriesPOJOmodel()
        whenever(lodjinhaRestRepository.getProductCategories())
            .thenReturn(Single.just(response))

        val expectedList = categoriesPOJOmodel().data?.mapNotNull {   it?.let { that-> categoryViewModelFrom(that) } }?.toMutableList()

        homeUseCase.getProductCategories()
            .test()
            .assertValue(expectedList)
    }



    @Test
    fun testProductDetailUseCases_getProductss_Completed() {
        val productCategoriesPayload = Mockito.mock(ProductsPayload::class.java)
        whenever(lodjinhaRestRepository.getProducts(1,0,3))
            .thenReturn(Single.just(productCategoriesPayload))

        productsUseCase.getProducts(1,0,3)
            .test()
            .assertComplete()
    }

    @Test
    fun testProductDetailUseCases_getProducts_Error() {
        val response = Throwable("Error response")
        whenever(lodjinhaRestRepository.getProducts(1,0,3))
            .thenReturn(Single.error(response))

        productsUseCase.getProducts(1,0,3)
            .test()
            .assertError(response)

    }

    @Test
    fun testProductDetailUseCases_getProducts_response() {
        val response = productsListPOJOmodel()
        whenever(lodjinhaRestRepository.getProducts(1,0,3))
            .thenReturn(Single.just(response))

        val expectedList = productsListPOJOmodel().data?.map {   productViewModelFrom(it) }?.toMutableList()

        productsUseCase.getProducts(1,0,3)
            .test()
            .assertValue(expectedList)
    }
}