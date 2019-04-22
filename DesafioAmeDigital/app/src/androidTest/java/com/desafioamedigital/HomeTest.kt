package com.desafioamedigital

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.desafioamedigital.ui.activity.home.HomeActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeTest {

    @Rule
    @JvmField
    val rule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun menuItemAboutClick(){
        home {
            clickAboutMenuItem()
        }
    }

    @Test
    fun recyclerViewCategoriesItemClick(){
        home {
            sleep(2000)
            clickCategoriesItem()
        }
    }

    @Test
    fun reserveProductFromCategories(){
        home{
            sleep(2000)
            clickCategoriesItem()
        }
        category {
            sleep(1000)
            clickProductItem()
        }
        product {
            clickToReserve()
            sleep(1000)
            checkAlertDialogMessage()
        }
    }

    @Test
    fun reserveProductFromBestSellers(){
        home {
            sleep(2000)
            clickBestSellersItem()
        }
        product {
            clickToReserve()
            sleep(1000)
            checkAlertDialogMessage()
        }
    }

}