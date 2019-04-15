package br.com.dafle.alodjinha.ui.home

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import br.com.dafle.alodjinha.R
import br.com.dafle.alodjinha.service.HomeService
import br.com.dafle.alodjinha.service.ProductService
import br.com.dafle.alodjinha.ui.main.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.standalone.inject
import org.koin.test.KoinTest

@RunWith(AndroidJUnit4::class)
@LargeTest
class HomeFragmentUITest: KoinTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(MainActivity::class.java, true, false)

    private val homeBusinnes: HomeService by inject()

    private val productBusinnes: ProductService by inject()

    @Test
    fun mainActivity__homeFragment__startActivityWithEmptyData() {
        itens(homeBusinnes, productBusinnes, activityRule) {
            startActvityWithEmptyData()
            checkEmptyStateAndList()
        }
    }

    @Test
    fun mainActivity__homeFragment__startActivityWithBannerData() {
        itens(homeBusinnes, productBusinnes, activityRule) {
            startActivityWithBannerData()
            Thread.sleep(1000)
            checkBannerData()
        }
    }

    @Test
    fun mainActivity__homeFragment__startActivityWithCategoryData() {
        itens(homeBusinnes, productBusinnes, activityRule) {
            startActivityWithBannerAndCategoryData()
            Thread.sleep(1000)
            checkBannerAndCategoryData()
        }
    }

    @Test
    fun mainActivity__homeFragment__startActivityWithFullData() {
        itens(homeBusinnes, productBusinnes, activityRule) {
            startActivityWithFullData()
            Thread.sleep(1500)
            checkFullData()
        }
    }

    @Test
    fun mainActivity__homeFragment__startActivityWithError() {
        itens(homeBusinnes, productBusinnes, activityRule) {
            startActivityWithError()
            Thread.sleep(300)
            checkHowShouldHehaveInCaseOfAnError()
        }
    }
}