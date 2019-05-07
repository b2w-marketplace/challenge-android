package br.com.amedigital.navigatedrawer

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.DrawerActions.open
import android.support.test.espresso.contrib.DrawerMatchers.isClosed
import android.support.test.espresso.contrib.DrawerMatchers.isOpen
import android.support.test.espresso.contrib.NavigationViewActions.navigateTo
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.Gravity
import br.com.amedigital.MainActivity
import br.com.amedigital.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
open class NavigationScreenTest {

    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun clickOnAboutNavigationItem_ShowsAboutScreen() {
        onView(withId(R.id.drawer))
            .check(matches(isClosed(Gravity.LEFT)))
            .perform(open())
        Thread.sleep(1000)

        onView(withId(R.id.navigationView))
            .perform(navigateTo(R.id.drawer_about))
        Thread.sleep(1000)

        val expectedAboutText = InstrumentationRegistry.getTargetContext()
            .getString(R.string.a_lojinha)
        onView(withId(R.id.textViewTitle)).check(matches(withText(expectedAboutText)))
        Thread.sleep(1000)
    }
}