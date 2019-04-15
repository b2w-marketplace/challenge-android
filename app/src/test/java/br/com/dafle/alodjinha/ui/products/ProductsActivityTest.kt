package br.com.dafle.alodjinha.ui.products

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.dafle.alodjinha.LodjinhaApplication
import br.com.dafle.alodjinha.business.HomeBusinnes
import br.com.dafle.alodjinha.business.ProductBusinnes
import br.com.dafle.alodjinha.model.Category
import br.com.dafle.alodjinha.model.Product
import br.com.dafle.alodjinha.model.ProductResponse
import io.reactivex.Observable
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext
import org.koin.standalone.inject
import org.koin.test.KoinTest
import org.koin.test.declareMock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ProductsActivityTest: KoinTest {

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    private val viewModel: ProductsViewModel by inject()

    private val productBusinnes: ProductBusinnes by inject()

    @Before
    fun setup() {
        StandAloneContext.startKoin(listOf(
                module {
                    single { ProductBusinnes(get()) }
                    single { Application() }
                    viewModel { ProductsViewModel(get(), get()) }
                }
        ))
        declareMock<ProductBusinnes>()
        declareMock<Application>()
    }

    @After
    fun tearDown() {
        StandAloneContext.stopKoin()
    }

    @Test
    fun should_fetchProductsSuccess() {
        `when`(productBusinnes.all(0,20,1))
                .thenReturn(Observable.just(
                        ProductResponse(
                                listOf(
                                        Product(Category("category description", 1, "http://qualquercoisa.com"), "product description", 1, "product_name", 9999.0, 7777.87, "url product"),
                                        Product(Category("category description another", 1, "http://qualquercoisa.com"), "product description", 1, "product_name", 399.0, 277.87, "url product")
                                ), 0, 75)
                ))
        viewModel.fetchProducts(1, false)
        assertEquals(2, viewModel.items.value?.size)
        assertNull(viewModel.errorState.value)
    }

    @Test
    fun should_fetchEmpty() {
        `when`(productBusinnes.all(0,20,1))
                .thenReturn(Observable.just(ProductResponse(listOf(), 0,0)))

        viewModel.fetchProducts(1, false)
        assertEquals(0, viewModel.items.value?.size)
        assertNull(viewModel.errorState.value)
    }

    @Test
    fun should_fetchError() {
        `when`(productBusinnes.all(0,20,1))
                .thenReturn(Observable.error(Throwable("Any error")))

        viewModel.fetchProducts(1, false)

        assertEquals(null, viewModel.items.value)
        assertNotNull(viewModel.errorState.value)
    }
}