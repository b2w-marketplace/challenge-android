package br.com.rbueno.lodjinha.home

import androidx.test.espresso.intent.Intents
import br.com.rbueno.lodjinha.BaseInstrumentedTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class HomeActivityTest : BaseInstrumentedTest() {

    private val robot = HomeActivityRobot(this)

    @Before
    fun before() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun shouldViewScreenFilled_WhenApiSuccessReturns() {
        robot
            .givenSetupBannerResponse()
            .givenSetupCategoryResponse()
            .givenSetupMostSoldResponse()
            .givenStartActivity()
            .thenScreenFilled()
    }

    @Test
    fun shouldViewErrorScreen_WhenApiFailReturns() {
        robot
            .givenSetupBannerErrorResponse()
            .givenSetupCategoryResponse()
            .givenSetupMostSoldResponse()
            .givenStartActivity()
            .thenErrorViewIsVisible()
    }

    @Test
    fun shouldNavigateToProductList_WhenClickInCategory() {
        robot
            .givenSetupBannerResponse()
            .givenSetupCategoryResponse()
            .givenSetupMostSoldResponse()
            .givenStubToProductListActivity()
            .givenStartActivity()
            .whenClickInCategoryItem()
            .thenOpenProductList()
    }

    @Test
    fun shouldNavigateToProductDetail_WhenClickInProduct() {
        robot
            .givenSetupBannerResponse()
            .givenSetupCategoryResponse()
            .givenSetupMostSoldResponse()
            .givenStubToProductDetailActivity()
            .givenStartActivity()
            .whenClickInProductItem()
            .thenOpenProductDetail()
    }
}