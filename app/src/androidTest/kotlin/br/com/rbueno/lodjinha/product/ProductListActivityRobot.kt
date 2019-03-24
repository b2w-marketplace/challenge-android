package br.com.rbueno.lodjinha.product

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import br.com.rbueno.lodjinha.BaseInstrumentedTest
import br.com.rbueno.lodjinha.R
import br.com.rbueno.lodjinha.ui.home.PRODUCT_ID_ARG
import br.com.rbueno.lodjinha.ui.home.TOOLBAR_TITLE_ARG
import br.com.rbueno.lodjinha.ui.product.detail.ProductDetailActivity
import br.com.rbueno.lodjinha.ui.product.list.ProductListActivity
import org.hamcrest.CoreMatchers
import org.junit.Rule

class ProductListActivityRobot(private val baseInstrumentedTest: BaseInstrumentedTest) {

    @get:Rule
    var activityTestRule = ActivityTestRule(ProductListActivity::class.java, false, false)

    fun givenSetupProductListResponse() = apply {
        baseInstrumentedTest.setupResponse(200, "product_list_response.json", "produto")
    }

    fun givenSetupProductListErrorResponse() = apply {
        baseInstrumentedTest.setupResponse(400, "product_list_response.json", "produto")
    }

    fun givenStartActivity() = apply {
        activityTestRule.launchActivity(Intent())
    }

    fun givenStubProductDetailActivity() = apply {
        intending(hasComponent(ProductDetailActivity::class.java.name))
            .respondWith(Instrumentation.ActivityResult(Activity.RESULT_OK, Intent()))
    }

    fun whenClickInProduct() = apply {
        onView(withId(R.id.recycler_products)).perform(
            RecyclerViewActions
                .actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click())
        )
    }

    fun thenScreenFilled() = apply {
        onView(withId(R.id.recycler_products)).check(matches(isDisplayed()))
    }

    fun thenErrorViewIsVisible() = apply {
        onView(withId(R.id.button_try_again)).check(matches(isDisplayed()))
    }

    fun thenOpenProductDetail() = apply {
        intended(
            CoreMatchers.allOf(
                hasExtraWithKey(TOOLBAR_TITLE_ARG),
                hasExtraWithKey(PRODUCT_ID_ARG),
                hasComponent(ProductDetailActivity::class.java.name)
            )
        )
    }
}