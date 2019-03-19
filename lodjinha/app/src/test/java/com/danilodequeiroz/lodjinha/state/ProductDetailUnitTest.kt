package com.danilodequeiroz.lodjinha.state

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.danilodequeiroz.lodjinha.common.presentation.DefaultSingleState
import com.danilodequeiroz.lodjinha.common.presentation.ErrorSingleState
import com.danilodequeiroz.lodjinha.common.presentation.LoadingSingleState
import com.danilodequeiroz.lodjinha.common.presentation.SingleState
import com.danilodequeiroz.lodjinha.productdetail.domain.DetailedProducViewModel
import com.danilodequeiroz.lodjinha.productdetail.domain.ProductDetailUseCase
import com.danilodequeiroz.lodjinha.productdetail.presentation.ProductDetailViewModel
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
class ProductDetailUnitTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    val useCase = mock<ProductDetailUseCase>()
    val observerState = mock<Observer<SingleState>>()

    val viewmodel by lazy { ProductDetailViewModel(useCase, Schedulers.trampoline(), Schedulers.trampoline()) }

    @Before
    fun initTest() {
        Mockito.reset(useCase, observerState)
    }

    @Test
    fun testBannerList_initBanners_FetchOnce() {
        val response = DetailedProducViewModel()
        whenever(useCase.getProduct(ArgumentMatchers.anyInt()))
            .thenReturn(Single.just(response))

        viewmodel.stateProduct.observeForever(observerState)
        viewmodel.pullProduct(0)

        verify(useCase).getProduct(0)

        val argumentCaptor = ArgumentCaptor.forClass(SingleState::class.java)
        val expectedInitialState = DefaultSingleState(null)
        val expectedLoadingState = LoadingSingleState(null)
        val expectedDefaultState = DefaultSingleState(response)

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
        whenever(useCase.getProduct(ArgumentMatchers.anyInt()))
            .thenReturn(Single.error(response))

        viewmodel.stateProduct.observeForever(observerState)
        viewmodel.pullProduct(0)

        verify(useCase).getProduct(0)

        val argumentCaptor = ArgumentCaptor.forClass(SingleState::class.java)
        val expectedInitialState = DefaultSingleState(null)
        val expectedLoadingState = LoadingSingleState(null)
        val expectedErrorState =
            ErrorSingleState(errorMessage, null)

        argumentCaptor.run {
            verify(observerState, Mockito.times(3)).onChanged(capture())

            val (initialState, loadingState, errorState) = allValues

            assertEquals(initialState, expectedInitialState)
            assertEquals(loadingState, expectedLoadingState)
            assertEquals(errorState, expectedErrorState)
        }
    }
}
