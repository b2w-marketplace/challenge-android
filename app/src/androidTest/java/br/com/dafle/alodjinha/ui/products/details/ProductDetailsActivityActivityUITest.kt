package br.com.dafle.alodjinha.ui.products.details

import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import br.com.dafle.alodjinha.service.ProductService
import br.com.dafle.alodjinha.ui.products.ProductsActvity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.standalone.inject
import org.koin.test.KoinTest

@RunWith(AndroidJUnit4::class)
@LargeTest
class ProductDetailsActivityActivityUITest: KoinTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(ProductDetailsActivity::class.java, true, false)

    private val productBusinnes: ProductService by inject()

    @Test
    fun productDetailsActivity__checkFieldsData() {
        itens(productBusinnes, activityRule) {
            startActivityWithData()
            checkFieldFromFetchedProduct()
        }
    }
}