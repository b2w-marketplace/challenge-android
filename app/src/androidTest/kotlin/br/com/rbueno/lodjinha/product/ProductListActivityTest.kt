package br.com.rbueno.lodjinha.product

import androidx.test.espresso.intent.Intents
import br.com.rbueno.lodjinha.BaseInstrumentedTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class ProductListActivityTest : BaseInstrumentedTest() {
    private val robot = ProductListActivityRobot(this)

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
            .givenSetupProductListResponse()
            .givenStartActivity()
            .thenScreenFilled()
    }

    @Test
    fun shouldViewErrorScreen_WhenApiFailReturns() {
        robot
            .givenSetupProductListErrorResponse()
            .givenStartActivity()
            .thenErrorViewIsVisible()
    }

    @Test
    fun shouldOpenDetailActivity_WhenClickInProduct() {
        robot
            .givenSetupProductListResponse()
            .givenStubProductDetailActivity()
            .givenStartActivity()
            .whenClickInProduct()
            .thenOpenProductDetail()
    }
}