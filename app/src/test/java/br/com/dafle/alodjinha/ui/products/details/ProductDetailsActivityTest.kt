package br.com.dafle.alodjinha.ui.products.details

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.dafle.alodjinha.LodjinhaApplication
import br.com.dafle.alodjinha.business.ProductBusinnes
import br.com.dafle.alodjinha.model.Category
import br.com.dafle.alodjinha.model.Product
import io.reactivex.Observable
import junit.framework.TestCase.*
import org.junit.After
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
class ProductDetailsActivityTest: KoinTest {

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    private val viewModel: ProductDetailsViewModel by inject()

    private val productBusinnes: ProductBusinnes by inject()

    @Before
    fun setup() {
        StandAloneContext.startKoin(listOf(
                module {
                    single { ProductBusinnes(get()) }
                    single { Application() }
                    viewModel { ProductDetailsViewModel(get(), get()) }
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
    fun should_fetchProductSuccess() {
        `when`(productBusinnes.get(1))
                .thenReturn(Observable.just(Product(Category("category description", 1, "http://qualquercoisa.com"), "product description", 1, "product_name", 9999.0, 7777.87, "url product")))

        viewModel.fetch(1)

        assertNotNull(viewModel.product.value)
        assertEquals("category description", viewModel.product.value?.categoria?.descricao)
        assertEquals(1, viewModel.product.value?.categoria?.id)
        assertEquals("http://qualquercoisa.com", viewModel.product.value?.categoria?.urlImagem)
        assertEquals("product description", viewModel.product.value?.descricao)
        assertEquals(1, viewModel.product.value?.id)
        assertEquals("product_name", viewModel.product.value?.nome)
        assertEquals(9999.0, viewModel.product.value?.precoDe)
        assertEquals(7777.87, viewModel.product.value?.precoPor)
        assertEquals("url product", viewModel.product.value?.urlImagem)
        assertNull(viewModel.errorState.value)
    }

    @Test
    fun should_fetchProductError() {
        `when`(productBusinnes.get(1))
                .thenReturn(Observable.error(Throwable("any string")))

        viewModel.fetch(1)

        assertNull(viewModel.product.value)
        assertNotNull(viewModel.errorState.value)
    }
}