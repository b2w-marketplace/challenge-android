package br.com.dafle.alodjinha.ui.products.details

import android.content.Intent
import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import br.com.dafle.alodjinha.R
import br.com.dafle.alodjinha.model.Category
import br.com.dafle.alodjinha.model.Product
import br.com.dafle.alodjinha.model.ProductResponse
import br.com.dafle.alodjinha.service.ProductService
import br.com.dafle.alodjinha.ui.products.ProductsActvity
import br.com.dafle.alodjinha.util.toCoin
import io.reactivex.Observable
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import org.hamcrest.Matchers.not
import org.mockito.Mockito
import org.mockito.Mockito.`when`

fun itens(productBusinnes: ProductService, testRule: ActivityTestRule<ProductDetailsActivity>,
          func: ProductDetailsRobot.() -> Unit) = ProductDetailsRobot(productBusinnes, testRule).apply { func() }

class ProductDetailsRobot(private var productBusinnes: ProductService,
                          private var testRule: ActivityTestRule<ProductDetailsActivity>) {

    private val intent = Intent().apply {
        putExtra(ProductDetailsActivity.EXTRA_PRODUCT,
                Product(
                        Category("category_description",1, "http://link.com"),
                        "description_product",
                        1, "name",
                        1234.56,
                        3444.45,
                        "http://blablabla.com"))
    }

    fun startActivityWithData() {
        `when`(productBusinnes.get(1))
                .thenReturn(Observable.just(Product(
                        Category("category_description",1, "http://link.com"),
                        "description_product",
                        1,
                        "name",
                        1234.56,
                        3444.45,
                        "http://blablabla.com")))


        `when`(productBusinnes.reserve(1))
                .thenReturn(Observable.just(Unit))

        testRule.launchActivity(intent)
    }

    fun checkFieldFromFetchedProduct() {
        onView(withId(R.id.tvProductName)).check(matches(withText("name")))
        onView(withId(R.id.tvFromValue)).check(matches(withText(1234.56.toCoin())))
        onView(withId(R.id.tvToValue)).check(matches(withText(3444.45.toCoin())))
        onView(withId(R.id.tvDescription)).check(matches(withText("description_product")))
        assertNotNull(testRule.activity.viewModel.product.value)
        assertNull(testRule.activity.viewModel.errorState.value)

        onView(withId(R.id.floatingActionButton)).perform(click())
        Thread.sleep(1000)
        onView(withText("OK")).perform(click())
        Thread.sleep(1000)
    }
}