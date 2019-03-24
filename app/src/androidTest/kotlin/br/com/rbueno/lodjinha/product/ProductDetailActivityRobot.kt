package br.com.rbueno.lodjinha.product

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import br.com.concretesolutions.requestmatcher.model.HttpMethod
import br.com.rbueno.lodjinha.BaseInstrumentedTest
import br.com.rbueno.lodjinha.R
import br.com.rbueno.lodjinha.ui.product.detail.ProductDetailActivity
import okhttp3.mockwebserver.MockResponse
import org.junit.Rule

class ProductDetailActivityRobot(private val baseInstrumentedTest: BaseInstrumentedTest) {

    @get:Rule
    var activityTestRule = ActivityTestRule(ProductDetailActivity::class.java, false, false)

    fun givenStartActivity() = apply {
        activityTestRule.launchActivity(Intent())
    }

    fun givenSetupProductDetailResponse() = apply {
        baseInstrumentedTest.setupResponse(200, "product_detail_response.json", "/produto/1")
    }

    fun givenSetupProductDetailErrorResponse() = apply {
        baseInstrumentedTest.setupResponse(400, "product_detail_response.json", "/produto/1")
    }

    fun givenReserveResponse() = apply {
        baseInstrumentedTest.serverRule.addResponse(MockResponse().setResponseCode(200))
            .ifRequestMatches()
            .pathIs("/produto/1")
            .methodIs(HttpMethod.POST)
    }

    fun givenReserveErrorResponse() = apply {
        baseInstrumentedTest.serverRule.addResponse(MockResponse().setResponseCode(400))
            .ifRequestMatches()
            .pathIs("/produto/1")
            .methodIs(HttpMethod.POST)
    }

    fun whenClickInReserveButton() = apply {
        onView(withId(R.id.floating_reserve_product)).perform(click())
    }

    fun thenScreenFilled() = apply {
        onView(withId(R.id.text_new_price)).check(matches(isDisplayed()))
    }

    fun thenErrorViewIsVisible() = apply {
        onView(withId(R.id.button_try_again)).check(matches(isDisplayed()))
    }

    fun thenSuccessAlertIsVisible() = apply {
        onView(withText(R.string.reserve_product_success)).check(matches(isDisplayed()))
    }

    fun thenErrorAlertIsVisible() = apply {
        onView(withText(R.string.error_default_message)).check(matches(isDisplayed()))
    }
}