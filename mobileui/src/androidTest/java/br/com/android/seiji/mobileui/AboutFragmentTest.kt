package br.com.android.seiji.mobileui

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import br.com.android.seiji.mobileui.ui.MainActivity
import br.com.android.seiji.mobileui.ui.about.AboutFragment
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AboutFragmentTest {

    private val testActivityRule = ActivityTestRule(
        MainActivity::class.java, true, true
    )

    @Rule
    fun rule() = testActivityRule

    @Before
    fun setUp() {
        rule().activity.replaceFragment(AboutFragment())
    }

    @Test
    fun loadFragmentShouldShowImageLogo() {
        onView(withId(R.id.imageLogo)).check(matches(isDisplayed()))
    }

    @Test
    fun loadFragmentShouldShowTextDevName() {
        onView(withId(R.id.textDevName)).check(matches(withText(R.string.developer_name)))
    }

    @Test
    fun loadFragmentShouldShowTextDevelopDate() {
        onView(withId(R.id.textDevelopDate)).check(matches(withText(R.string.develop_date)))
    }
}