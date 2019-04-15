package br.com.dafle.alodjinha.ui.products

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import br.com.dafle.alodjinha.R
import br.com.dafle.alodjinha.matcher.hasItemCount
import br.com.dafle.alodjinha.model.Category
import br.com.dafle.alodjinha.model.Product
import br.com.dafle.alodjinha.model.ProductResponse
import br.com.dafle.alodjinha.service.ProductService
import io.reactivex.Observable
import junit.framework.TestCase.*
import org.mockito.Mockito.`when`


fun itens(productBusinnes: ProductService, testRule: ActivityTestRule<ProductsActvity>,
          func: ProductsRobot.() -> Unit) = ProductsRobot(productBusinnes, testRule).apply { func() }

class ProductsRobot(private var productBusinnes: ProductService,
                    private var testRule: ActivityTestRule<ProductsActvity>)  {

    private val intent = Intent().apply {
        putExtra(ProductsActvity.EXTRA_CATEGORY, Category("MOCKITO_E_TOPP", 1, "http://this_is_url.com"))
    }

    fun startActvityWithEmptyData() {
        `when`(productBusinnes.all(0, 20, 1))
                .thenReturn(Observable.just(ProductResponse(listOf(),0,34)))
        testRule.launchActivity(intent)
    }

    fun startActivityWithData() {
        `when`(productBusinnes.all(0,20,1))
                .thenReturn(Observable.just(
                        ProductResponse(
                                listOf(
                                        Product(Category("category description", 1, "http://qualquercoisa.com"), "product description", 1, "product_name", 9999.0, 7777.87, "url product"),
                                        Product(Category("category description another", 1, "http://qualquercoisa.com"), "product description", 1, "product_name", 399.0, 277.87, "url product"),
                                        Product(Category("category description another", 1, "http://qualquercoisa.com"), "product description", 1, "product_name", 399.0, 277.87, "url product"),
                                        Product(Category("category description another", 1, "http://qualquercoisa.com"), "product description", 1, "product_name", 399.0, 277.87, "url product"),
                                        Product(Category("category description another", 1, "http://qualquercoisa.com"), "product description", 1, "product_name", 399.0, 277.87, "url product"),
                                        Product(Category("category description another", 1, "http://qualquercoisa.com"), "product description", 1, "product_name", 399.0, 277.87, "url product"),
                                        Product(Category("category description another last", 1, "http://qualquercoisa.com"), "product description", 1, "product_name", 399.0, 277.87, "url product")
                                ), 0, 75)
                ))
        testRule.launchActivity(intent)
    }

    fun startActivityWithError() {
        `when`(productBusinnes.all(0,20,1))
                .thenReturn(Observable.error(Throwable("any string")))

        testRule.launchActivity(intent)
    }

    fun checkEmptyStateAndList() {
        onView(withId(R.id.recyclerView)).check(matches(hasItemCount(0)))
        Thread.sleep(2000)
        onView(withId(R.id.tvEmpty)).check(matches(withText("Nenhum item na categoria MOCKITO_E_TOPP")))
        assertEquals(0, testRule.activity.viewModel.items.value?.size)
    }

    fun checkFullData() {
        onView(withId(R.id.recyclerView)).check(matches(hasItemCount(7)))
        assertEquals(7, testRule.activity.viewModel.items.value?.size)
    }

    fun checkError() {
        onView(withText("OK")).perform(ViewActions.click())
        onView(withId(R.id.recyclerView)).check(matches(hasItemCount(0)))
        assertNull(testRule.activity.viewModel.items.value)
        assertNotNull(testRule.activity.viewModel.errorState.value)
    }
}