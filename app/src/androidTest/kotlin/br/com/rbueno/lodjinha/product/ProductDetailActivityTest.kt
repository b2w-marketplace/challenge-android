package br.com.rbueno.lodjinha.product

import br.com.rbueno.lodjinha.BaseInstrumentedTest
import org.junit.Test

class ProductDetailActivityTest: BaseInstrumentedTest() {

    private val robot = ProductDetailActivityRobot(this)

    @Test
    fun shouldViewScreenFilled_WhenApiSuccessReturns() {
        robot
            .givenSetupProductDetailResponse()
            .givenStartActivity()
            .thenScreenFilled()
    }

    @Test
    fun shouldViewErrorScreen_WhenApiFailReturns() {
        robot
            .givenSetupProductDetailErrorResponse()
            .givenStartActivity()
            .thenErrorViewIsVisible()
    }

    @Test
    fun shouldViewSuccessAlert_WhenClickInReserveButtonAndApiReturnSuccess() {
        robot
            .givenSetupProductDetailResponse()
            .givenReserveResponse()
            .givenStartActivity()
            .whenClickInReserveButton()
            .thenSuccessAlertIsVisible()
    }

    @Test
    fun shouldViewErrorAlert_WhenClickInReserveButtonAndApiReturnFail() {
        robot
            .givenSetupProductDetailResponse()
            .givenReserveErrorResponse()
            .givenStartActivity()
            .whenClickInReserveButton()
            .thenErrorAlertIsVisible()
    }
}