package br.com.rbueno.lodjinha.home

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.*
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.matcher.IntentMatchers.*
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import br.com.rbueno.lodjinha.BaseInstrumentedTest
import br.com.rbueno.lodjinha.R
import br.com.rbueno.lodjinha.ui.home.CATEGORY_ID_ARG
import br.com.rbueno.lodjinha.ui.home.HomeActivity
import br.com.rbueno.lodjinha.ui.home.PRODUCT_ID_ARG
import br.com.rbueno.lodjinha.ui.home.TOOLBAR_TITLE_ARG
import br.com.rbueno.lodjinha.ui.product.detail.ProductDetailActivity
import br.com.rbueno.lodjinha.ui.product.list.ProductListActivity
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule

class HomeActivityRobot(private val baseInstrumentedTest: BaseInstrumentedTest) {

    @get:Rule
    var activityTestRule = ActivityTestRule(HomeActivity::class.java, false, false)


    fun givenSetupBannerResponse() = apply {
        baseInstrumentedTest.setupResponse(200, "banner_response.json", "banner")
    }

    fun givenSetupCategoryResponse() = apply {
        baseInstrumentedTest.setupResponse(200, "category_response.json", "categoria")
    }

    fun givenSetupMostSoldResponse() = apply {
        baseInstrumentedTest.setupResponse(200, "most_sold_response.json", "produto/maisvendidos")
    }

    fun givenStartActivity() = apply {
        activityTestRule.launchActivity(Intent())
    }

    fun givenSetupBannerErrorResponse() = apply {
        baseInstrumentedTest.setupResponse(400, "banner_response.json", "banner")
    }

    fun givenStubToProductListActivity() = apply {
        intending(hasComponent(ProductListActivity::class.java.name))
            .respondWith(Instrumentation.ActivityResult(Activity.RESULT_OK, Intent()))
    }

    fun givenStubToProductDetailActivity() = apply {
        intending(hasComponent(ProductDetailActivity::class.java.name))
            .respondWith(Instrumentation.ActivityResult(Activity.RESULT_OK, Intent()))
    }

    fun whenClickInCategoryItem() = apply {
        onView(withId(R.id.recycler_category)).perform(
            RecyclerViewActions
                .actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )
    }

    fun whenClickInProductItem() = apply {
        onView(withId(R.id.recycler_products)).perform(
            RecyclerViewActions
                .actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )
    }

    fun thenScreenFilled() = apply {
        onView(withId(R.id.view_pager_banner)).check(matches(isDisplayed()))
        onView(withId(R.id.recycler_category)).check(matches(isDisplayed()))
        onView(withId(R.id.recycler_products)).check(matches(isDisplayed()))
    }

    fun thenErrorViewIsVisible() = apply {
        onView(withId(R.id.button_try_again)).check(matches(isDisplayed()))
    }

    fun thenOpenProductList() = apply {
        intended(
            allOf(
                hasExtraWithKey(TOOLBAR_TITLE_ARG),
                hasExtraWithKey(CATEGORY_ID_ARG),
                hasComponent(ProductListActivity::class.java.name)
            )
        )
    }

    fun thenOpenProductDetail() = apply {
        intended(
            allOf(
                hasExtraWithKey(TOOLBAR_TITLE_ARG),
                hasExtraWithKey(PRODUCT_ID_ARG),
                hasComponent(ProductDetailActivity::class.java.name)
            )
        )
    }
}