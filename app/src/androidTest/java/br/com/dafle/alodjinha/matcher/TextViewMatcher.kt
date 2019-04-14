package br.com.dafle.alodjinha.matcher

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description

fun withTextColor(colorId: Int) = object : BoundedMatcher<View, TextView>(TextView::class.java) {

    override fun describeTo(description: Description?) {
        description?.appendText("checking textColor is $colorId")
    }

    override fun matchesSafely(item: TextView?) = if (item != null) {
        item.currentTextColor == ContextCompat.getColor(item.context, colorId)
    } else {
        false
    }
}