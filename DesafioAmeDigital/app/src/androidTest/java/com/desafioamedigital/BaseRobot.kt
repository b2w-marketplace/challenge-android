package com.desafioamedigital

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.v7.widget.RecyclerView

open class BaseRobot {

    fun menuItemClick(itemTitle: Int): ViewInteraction = onView(withText(itemTitle))

    fun recyclerViewItemClick(recyclerViewId: Int): ViewInteraction = onView(withId(recyclerViewId))
        .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

    fun viewClick(viewId: Int): ViewInteraction = onView(withId(viewId)).perform(click())

    fun stringMatches(viewId: Int, stringId: Int): ViewInteraction = onView(withId(viewId))
        .check(ViewAssertions.matches(withText(stringId)))

    fun dialogText(stringId: Int): ViewInteraction = onView(withText(stringId)).check(ViewAssertions.matches(isDisplayed()))

    fun sleep(millis: Long) = apply {
        Thread.sleep(millis)
    }

}