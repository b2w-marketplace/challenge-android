package com.b2w.lodjinha.about

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.doesNotExist
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.b2w.lodjinha.R
import com.b2w.lodjinha.ui.about.AboutFragment
import com.b2w.lodjinha.util.ContainerActivityFragment
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AboutFragmentTest {

    val testActivityRule = ActivityTestRule(ContainerActivityFragment::class.java, true, true)

    @Rule
    fun rule() = testActivityRule

    @Before
    fun setUp() {
        rule().activity.setFragment(AboutFragment())
    }

    @Test
    fun loadPage_shouldshowimage(){
        onView(withId(R.id.about_logo)).check(matches(isDisplayed()))
    }

    @Test
    fun loadPage_shouldshowdevname(){
        onView(withId(R.id.about_app_dev_name)).check(matches(withText(R.string.about_dev_name)))
    }

    @Test
    fun loadPage_shouldshowdate(){
        onView(withId(R.id.about_app_dev_date)).check(matches(withText(R.string.about_dev_date)))
    }
}