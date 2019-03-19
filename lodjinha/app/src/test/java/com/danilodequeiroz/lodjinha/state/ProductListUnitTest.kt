package com.danilodequeiroz.lodjinha.state

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.danilodequeiroz.lodjinha.common.presentation.*
import com.danilodequeiroz.lodjinha.home.domain.ProductViewModel
import com.danilodequeiroz.lodjinha.productlist.domain.ProductsListUseCase
import com.danilodequeiroz.lodjinha.productlist.presentation.ProductListViewModel
import common.mock
import common.whenever
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.Mockito.verify

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ProductListUnitTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    val useCase = mock<ProductsListUseCase>()
    val observerState = mock<Observer<ListState>>()

    val viewmodel by lazy { ProductListViewModel(useCase, Schedulers.trampoline(), Schedulers.trampoline()) }

    @Before
    fun initTest() {
        Mockito.reset(useCase, observerState)
    }

    @Test
    fun testBannerList_initBanners_FetchOnce() {
        val response = mutableListOf(ProductViewModel())
        whenever(useCase.getProducts(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt()))
            .thenReturn(Single.just(response))

        val categoryId =0
        viewmodel.stateProductsList.observeForever(observerState)
        viewmodel.updateProducts(categoryId)


        verify(useCase).getProducts(ArgumentMatchers.anyInt(),ArgumentMatchers.anyInt(),ArgumentMatchers.anyInt())

        val argumentCaptor = ArgumentCaptor.forClass(ListState::class.java)
        val expectedInitialState = DefaultState(emptyList(), false, 0)
        val expectedLoadingState = LoadingState(emptyList(), false, 0)
        val expectedDefaultState = DefaultState(response, false, 0)

        argumentCaptor.run {
            verify(observerState, Mockito.times(3)).onChanged(this.capture())

            val (initialState, loadingState, defaultState) = allValues

            assertEquals(initialState, expectedInitialState)
            assertEquals(loadingState, expectedLoadingState)
            assertEquals(defaultState, expectedDefaultState)
        }
    }

    @Test
    fun testBannerList_initBanners_EmptyList() {
        val response = mutableListOf<ProductViewModel>()
        whenever(useCase.getProducts(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt()))
            .thenReturn(Single.just(response))

        val categoryId =0
        viewmodel.stateProductsList.observeForever(observerState)
        viewmodel.updateProducts(categoryId)


        verify(useCase).getProducts(ArgumentMatchers.anyInt(),ArgumentMatchers.anyInt(),ArgumentMatchers.anyInt())

        val argumentCaptor = ArgumentCaptor.forClass(ListState::class.java)
        val expectedInitialState = DefaultState(emptyList(), false, 0)
        val expectedLoadingState = LoadingState(emptyList(), false, 0)
        val expectedDefaultState = EmptyListState(response, false, 0)

        argumentCaptor.run {
            verify(observerState, Mockito.times(3)).onChanged(this.capture())

            val (initialState, loadingState, defaultState) = allValues

            assertEquals(initialState, expectedInitialState)
            assertEquals(loadingState, expectedLoadingState)
            assertEquals(defaultState, expectedDefaultState)
        }
    }

    @Test
    fun testBannersList_initBestSellingProducts_Error() {
        val errorMessage = "Error response"
        val response = Throwable(errorMessage)
        whenever(useCase.getProducts(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt()))
            .thenReturn(Single.error(response))

        val categoryId =0
        viewmodel.stateProductsList.observeForever(observerState)
        viewmodel.updateProducts(categoryId)

        verify(useCase).getProducts(ArgumentMatchers.anyInt(),ArgumentMatchers.anyInt(),ArgumentMatchers.anyInt())

        val argumentCaptor = ArgumentCaptor.forClass(ListState::class.java)
        val expectedInitialState = DefaultState(emptyList(), false, 0)
        val expectedLoadingState = LoadingState(emptyList(), false, 0)
        val expectedErrorState =
            ErrorState(errorMessage, emptyList(), false, 0)

        argumentCaptor.run {
            verify(observerState, Mockito.times(3)).onChanged(capture())

            val (initialState, loadingState, errorState) = allValues

            assertEquals(initialState, expectedInitialState)
            assertEquals(loadingState, expectedLoadingState)
            assertEquals(errorState, expectedErrorState)
        }
    }
}
