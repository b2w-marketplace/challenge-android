package com.danilodequeiroz.lodjinha

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.danilodequeiroz.lodjinha.common.BaseApplication
import com.danilodequeiroz.lodjinha.common.util.toBRLMoneyString
import com.danilodequeiroz.webapi.longDesc
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class BasicFlowUiTest {


    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java,false, false)

    @Before
    fun setup(){
        BaseApplication.mock = true
    }

    @Test
    fun check_homeScrolled_allItems() {
        activityRule.launchActivity(null)

        onView(withId(R.id.recyclerProductCategories))
            .check(matches(atPosition(0, hasDescendant(withText("Games")))))
        onView(withId(R.id.recyclerProductCategories))
            .check(matches(atPosition(1, hasDescendant(withText("Livros")))))
        onView(withId(R.id.recyclerProductCategories))
            .check(matches(atPosition(2, hasDescendant(withText("Celulares")))))
        onView(withId(R.id.recyclerProductCategories))
            .check(matches(atPosition(3, hasDescendant(withText("Informática")))))

        onView(withId(R.id.recyclerBestSellingProducts))
            .check(matches(atPosition(0, hasDescendant(withText("Fifa 09 PS3 - Mídia Física"))))).check(matches(isDisplayed()))



        onView(withId(R.id.recyclerBestSellingProducts))
            .check(matches(atPosition(1, hasDescendant(withText("Fifa 10 PS3 - Mídia Física")))))

        onView(withId(R.id.recyclerBestSellingProducts))
            .check(matches(atPosition(10, hasDescendant(withText("Fifa 19 PS4 - Mídia Física")))))

        onView(withId(R.id.nestedScrollView))
            .perform(ViewActions.swipeUp())

        onView(withId(R.id.recyclerBestSellingProducts))
            .check(matches(atPosition(10, hasDescendant(withText("Fifa 19 PS4 - Mídia Física"))))).check(matches(isDisplayed()))


    }

    @Test
    fun check_detail_opens_from_product_lists() {
        activityRule.launchActivity(null)

        onView(withId(R.id.recyclerProductCategories))
            .check(matches(atPosition(0, hasDescendant(withText("Games"))))).perform(ViewActions.click())

        onView(withId(R.id.recyclerProducts))
            .check(matches(atPosition(0, hasDescendant(withText("Fifa 09 PS3 - Mídia Física"))))).check(matches(isDisplayed()))


        onView(withId(R.id.recyclerProducts))
            .check(matches(atPosition(1, hasDescendant(withText("Fifa 10 PS3 - Mídia Física")))))

        onView(withId(R.id.recyclerProducts))
            .perform(ViewActions.swipeUp())

        onView(withId(R.id.recyclerProducts))
            .check(matches(atPosition(10, hasDescendant(withText("Fifa 19 PS4 - Mídia Física"))))).check(matches(isDisplayed()))

        onView(withId(R.id.recyclerProducts))
            .check(matches(atPosition(10, hasDescendant(withText("Fifa 19 PS4 - Mídia Física"))))).perform(ViewActions.click())


        onView(withId(R.id.txtProductName))
            .check(matches(withText("Fifa 19 PS4 - Mídia Física")))


        onView(withId(R.id.txtOldPrice))
            .check(matches(withText("De ${279.toBRLMoneyString()}")))

        onView(withId(R.id.txtNewPrice))
            .check(matches(withText("Por ${172.9.toBRLMoneyString()}")))

        onView(withId(R.id.txtProductCategory))
            .check(matches(withText("Games")))


        onView(withId(R.id.txtProductDesc))
            .check(matches(withText(longDesc)))

    }

    @Test
    fun check_detail_opens_from_best_selling_procuts_lists() {
        activityRule.launchActivity(null)


        onView(withId(R.id.recyclerBestSellingProducts))
            .check(matches(atPosition(0, hasDescendant(withText("Fifa 09 PS3 - Mídia Física"))))).check(matches(isDisplayed()))


        onView(withId(R.id.recyclerBestSellingProducts))
            .check(matches(atPosition(1, hasDescendant(withText("Fifa 10 PS3 - Mídia Física")))))

        onView(withId(R.id.nestedScrollView))
            .perform(ViewActions.swipeUp())

        onView(withId(R.id.recyclerBestSellingProducts))
            .check(matches(atPosition(10, hasDescendant(withText("Fifa 19 PS4 - Mídia Física"))))).check(matches(isDisplayed()))

        onView(withId(R.id.recyclerBestSellingProducts))
            .check(matches(atPosition(10, hasDescendant(withText("Fifa 19 PS4 - Mídia Física"))))).perform(ViewActions.click())

        Thread.sleep(500)


        onView(withId(R.id.txtProductName))
            .check(matches(withText("Fifa 19 PS4 - Mídia Física")))


        onView(withId(R.id.txtOldPrice))
            .check(matches(withText("De ${279.toBRLMoneyString()}")))

        onView(withId(R.id.txtNewPrice))
            .check(matches(withText("Por ${172.9.toBRLMoneyString()}")))

        onView(withId(R.id.txtProductCategory))
            .check(matches(withText("Games")))


        onView(withId(R.id.txtProductDesc))
            .check(matches(withText(longDesc)))

    }
}
