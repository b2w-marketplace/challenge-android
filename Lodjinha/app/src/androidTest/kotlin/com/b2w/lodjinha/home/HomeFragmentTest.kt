package com.b2w.lodjinha.home

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import br.com.concretesolutions.requestmatcher.InstrumentedTestRequestMatcherRule
import com.b2w.lodjinha.ui.home.HomeFragment
import com.b2w.lodjinha.util.ContainerActivityFragment
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import br.com.concretesolutions.requestmatcher.RequestMatcherRule
import com.b2w.lodjinha.R

@RunWith(AndroidJUnit4::class)
class HomeFragmentTest {

    @Rule
    @JvmField val serverRule: RequestMatcherRule = InstrumentedTestRequestMatcherRule()

    val testActivityRule = ActivityTestRule(ContainerActivityFragment::class.java, true, true)

    @Rule
    fun rule() = testActivityRule

    @Before
    fun setUp() {
        serverRule.url("/")
    }

    @Test
    fun canShowBanners(){
        serverRule.addFixture(200, "banners.json")
        rule().activity.setFragment(HomeFragment())

        onView(withId(R.id.view_banners)).check(matches(isDisplayed()))
    }

    @Test
    fun canShowCategories(){
        serverRule.addFixture(200, "categories.json")
        rule().activity.setFragment(HomeFragment())

        onView(withId(R.id.view_banners)).check(matches(isDisplayed()))
    }

    @Test
    fun canShowBestSelling(){
        serverRule.addFixture(200, "bestselling.json")
        rule().activity.setFragment(HomeFragment())

        onView(withId(R.id.view_banners)).check(matches(isDisplayed()))
    }
}