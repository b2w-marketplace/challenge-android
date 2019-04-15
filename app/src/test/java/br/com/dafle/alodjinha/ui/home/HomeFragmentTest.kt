package br.com.dafle.alodjinha.ui.home

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.dafle.alodjinha.LodjinhaApplication
import br.com.dafle.alodjinha.business.HomeBusinnes
import br.com.dafle.alodjinha.business.ProductBusinnes
import br.com.dafle.alodjinha.model.*
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
class HomeFragmentTest: KoinTest {

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    private val viewModel: HomeViewModel by inject()

    private val homeBusiness: HomeBusinnes by inject()

    private val productBusinnes: ProductBusinnes by inject()

    @Before
    fun setup() {
        StandAloneContext.startKoin(listOf(
                module {
                    single { ProductBusinnes(get()) }
                    single { HomeBusinnes(get()) }
                    single { Application() }
                    viewModel { HomeViewModel(get(), get(), get()) }
                }
        ))
        declareMock<ProductBusinnes>()
        declareMock<HomeBusinnes>()
        declareMock<Application>()
    }

    @After
    fun tearDown() {
        StandAloneContext.stopKoin()
    }

    @Test
    fun should_fetchSuccess() {
        `when`(homeBusiness.fetchBanner())
                .thenReturn(Observable.just(
                        BannerResponse(
                                listOf(
                                        Banner(0, "http://this_is_any_a_example_link.com", "http://url.com"),
                                        Banner(1, "http://this_is_a_example_link.com", "http://url.com")
                                )
                        )
                ))
        `when`(homeBusiness.fetchCategory())
                .thenReturn(Observable.just(
                        CategoryResponse(
                                listOf(
                                        Category("any description 1", 0, "http://anywhere"),
                                        Category("any description 2", 1, "http://anywhere"),
                                        Category("any description 3", 2, "http://anywhere")
                                )
                        )
                ))
        `when`(productBusinnes.fetchBestSeller())
                .thenReturn(Observable.just(
                        ProductResponse(
                                listOf(
                                        Product(Category("category description", 1, "http://qualquercoisa.com"), "product description", 1, "product_name", 9999.0, 7777.87, "url product"),
                                        Product(Category("category description another", 1, "http://qualquercoisa.com"), "product description", 1, "product_name", 399.0, 277.87, "url product")
                                ), 0, 75)
                ))

        viewModel.fetch()

        assertEquals(2, viewModel.banners.value?.size)
        assertEquals("http://this_is_a_example_link.com", viewModel.banners.value?.get(1)?.linkUrl)
        assertEquals(3, viewModel.categories.value?.size)
        assertEquals("any description 3", viewModel.categories.value?.get(2)?.descricao)
        assertEquals(2, viewModel.bestSellers.value?.size)
        assertEquals("category description another", viewModel.bestSellers.value?.get(1)?.categoria?.descricao)
        assertEquals(null, viewModel.errorState.value)
        assertNull(viewModel.errorState.value)
    }

    @Test
    fun should_fetchEmpty() {
        `when`(homeBusiness.fetchBanner())
                .thenReturn(Observable.just(BannerResponse(listOf())))

        `when`(homeBusiness.fetchCategory())
                .thenReturn(Observable.just(CategoryResponse(listOf())))

        `when`(productBusinnes.fetchBestSeller())
                .thenReturn(Observable.just(ProductResponse(listOf(),0,34)))

        viewModel.fetch()

        assertEquals(0, viewModel.banners.value?.size)
        assertEquals(0, viewModel.categories.value?.size)
        assertEquals(0, viewModel.bestSellers.value?.size)
        assertNull(viewModel.errorState.value)
    }

    @Test
    fun should_fetchError() {
        `when`(homeBusiness.fetchBanner())
                .thenReturn(Observable.error(Throwable("Any error")))

        `when`(homeBusiness.fetchCategory())
                .thenReturn(Observable.error(Throwable("Any error")))

        `when`(productBusinnes.fetchBestSeller())
                .thenReturn(Observable.error(Throwable("Any error")))

        viewModel.fetch()

        assertEquals(null, viewModel.banners.value)
        assertEquals(null, viewModel.categories.value)
        assertEquals(null, viewModel.bestSellers.value)
        assertNotNull(viewModel.errorState.value)
    }
}