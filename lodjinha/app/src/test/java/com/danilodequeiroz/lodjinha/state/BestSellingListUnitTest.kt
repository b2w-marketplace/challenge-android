package com.danilodequeiroz.lodjinha.state

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.danilodequeiroz.lodjinha.common.presentation.DefaultState
import com.danilodequeiroz.lodjinha.common.presentation.ErrorState
import com.danilodequeiroz.lodjinha.common.presentation.ListState
import com.danilodequeiroz.lodjinha.common.presentation.LoadingState
import com.danilodequeiroz.lodjinha.home.domain.HomeUseCase
import com.danilodequeiroz.lodjinha.home.domain.ProductViewModel
import com.danilodequeiroz.lodjinha.home.presentation.*
import common.mock
import common.whenever
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mockito
import org.mockito.Mockito.verify

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class BestSellingListUnitTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    val bestSellingProductsUseCase = mock<HomeUseCase>()
    val observerState = mock<Observer<ListState>>()

    val viewmodel by lazy { HomeViewModel(bestSellingProductsUseCase, Schedulers.trampoline(), Schedulers.trampoline()) }

    @Before
    fun initTest() {
        Mockito.reset(bestSellingProductsUseCase, observerState)
    }

    @Test
    fun testBestSellingProductsList_initBestSellingProducts_FetchOnce() {
        val response = mutableListOf(ProductViewModel())
        whenever(bestSellingProductsUseCase.getAllBestSellingProducts())
            .thenReturn(Single.just(response))

        viewmodel.stateBestSellingProducts.observeForever(observerState)
        viewmodel.initBestSellingProducts()

        verify(bestSellingProductsUseCase).getAllBestSellingProducts()

        val argumentCaptor = ArgumentCaptor.forClass(ListState::class.java)
        val expectedInitialState = DefaultState(emptyList(), false, 0)
        val expectedLoadingState = LoadingState(emptyList(), false, 0)
        val expectedDefaultState = DefaultState(response, false, 0)

        argumentCaptor.run {
            verify(observerState, Mockito.times(3)).onChanged(this.capture())

            val (initialState,loadingState, defaultState) = allValues

            assertEquals(initialState, expectedInitialState)
            assertEquals(loadingState, expectedLoadingState)
            assertEquals(defaultState, expectedDefaultState)
        }
    }

    @Test
    fun testBestSellingProductsList_initBestSellingProducts_Error() {
        val errorMessage = "Error response"
        val response = Throwable(errorMessage)
        whenever(bestSellingProductsUseCase.getAllBestSellingProducts())
            .thenReturn(Single.error(response))

        viewmodel.stateBestSellingProducts.observeForever(observerState)
        viewmodel.initBestSellingProducts()

        verify(bestSellingProductsUseCase).getAllBestSellingProducts()

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
