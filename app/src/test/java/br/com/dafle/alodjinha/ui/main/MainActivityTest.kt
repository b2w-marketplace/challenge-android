package br.com.dafle.alodjinha.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.dafle.alodjinha.business.ProductBusinnes
import io.reactivex.Observable
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.StandAloneContext.stopKoin
import org.koin.standalone.inject
import org.koin.test.KoinTest
import org.koin.test.declareMock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainActivityTest: KoinTest {

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    private val viewModel: MainViewModel by inject()

    private val mBusiness: ProductBusinnes by inject()

    @Before
    fun setup() {
        startKoin(listOf(
                module {
                    single { ProductBusinnes(get()) }
                    viewModel { MainViewModel(get()) }
                }
        ))
        declareMock<ProductBusinnes>()
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun should_validateCountAndNameObject() {
        `when`(mBusiness.fetch())
                .thenReturn(Observable.just(listOf(
                        Coin("1", "Brasil", "R$"),
                        Coin("2", "EUA", "U$"),
                        Coin("3", "Canadá", "C$"),
                        Coin("4", "BitCoin", "C"),
                        Coin("5", "I dont know", "XPTO")
                )))

        viewModel.fetch()

        assertEquals(5, viewModel.list.value?.size)
        assertEquals("BitCoin", viewModel.list.value?.get(3)?.name)
    }

    @Test
    fun should_fetchCoinsWithError() {
        `when`(mBusiness.fetch())
                .thenReturn(Observable.error(Throwable()))
        viewModel.fetch()

        assertEquals(null, viewModel.list.value)
    }
}