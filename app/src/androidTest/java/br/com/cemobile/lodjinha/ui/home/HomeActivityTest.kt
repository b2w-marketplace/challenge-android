package br.com.cemobile.lodjinha.ui.home

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.DrawerActions
import android.support.test.espresso.contrib.DrawerMatchers.isClosed
import android.support.test.espresso.contrib.NavigationViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.view.Gravity
import br.com.cemobile.lodjinha.R
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@Suppress("DEPRECATION")
class HomeActivityTest {

    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(HomeActivity::class.java)

    @Before
    fun registerIdlingResource() {
        Espresso.registerIdlingResources(activityTestRule.getActivity().getCountingIdlingResource())
    }

    @After
    fun unregisterIdlingResource() {
        Espresso.unregisterIdlingResources(activityTestRule.getActivity().getCountingIdlingResource())
    }

    @Test
    fun checkIfLoadingIsDisplayed() {
        onView(withId(R.id.progressBar))
            .check(matches(isDisplayed()))
    }

    @Test
    fun clickOnAboutItemInDrawerMenu_ShowsAboutFragment() {
        onView(withId(R.id.drawerLayout))
            .check(matches(isClosed(Gravity.LEFT)))
            .perform(DrawerActions.open())

        onView(withId(R.id.navigationView))
            .perform(NavigationViewActions.navigateTo(R.id.nav_about))

        val expectedAppNameText = InstrumentationRegistry.getTargetContext()
            .getString(R.string.app_name)

        onView(withId(R.id.appNameText)).check(matches(withText(expectedAppNameText)));
    }

}