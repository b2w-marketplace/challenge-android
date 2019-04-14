package br.com.dafle.alodjinha.ui.main

import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import br.com.dafle.alodjinha.service.ProductService
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.standalone.inject
import org.koin.test.KoinTest

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityUITest: KoinTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(MainActivity::class.java, true, false)

    private val mBusiness: ProductService by inject()

    @Test
    fun coinsActivity__startActivityWithEmptyData() {
        itens(mBusiness, activityRule) {
            startActivityWithZeroCoins()
            checkEmptyStateAndList()
        }
    }

    @Test
    fun coinsActivity_startActivityWithData() {
        itens(mBusiness, activityRule) {
            startActivityWithEightCoinsList()
            checkListWithData()
        }
    }

    @Test
    fun coinsActivity_startActivityWithErrorCallCoins() {
        itens(mBusiness, activityRule) {
            startActivityWithErrorCallCoins()
            checkIfListIsNull()
        }
    }
}