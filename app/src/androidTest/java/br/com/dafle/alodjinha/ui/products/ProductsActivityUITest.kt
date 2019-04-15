package br.com.dafle.alodjinha.ui.products

import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import br.com.dafle.alodjinha.service.ProductService
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.standalone.inject
import org.koin.test.KoinTest


@RunWith(AndroidJUnit4::class)
@LargeTest
class ProductsActivityUITest: KoinTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(ProductsActvity::class.java, true, false)

    private val productBusinnes: ProductService by inject()

    @Test
    fun mainActivity__startActivityWithEmptyData() {
        itens(productBusinnes, activityRule) {
            startActvityWithEmptyData()
            checkEmptyStateAndList()
        }
    }

    @Test
    fun mainAcivity__startActivityWithData() {
        itens(productBusinnes, activityRule) {
            startActivityWithData()
            checkFullData()
        }
    }

    @Test
    fun mainActivity__startActivityWithError() {
        itens(productBusinnes, activityRule) {
            startActivityWithError()
            checkError()
        }
    }
}