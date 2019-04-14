package br.com.dafle.alodjinha.matcher

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description

fun withBackgroundGradientColor(drawableId: Int) =
        object : BoundedMatcher<View, View>(View::class.java) {

            override fun describeTo(description: Description?) {
                description?.appendText("checking background is $drawableId")
            }

            override fun matchesSafely(item: View?) = if (item != null) {
                val desired =
                        item.resources.getDrawable(drawableId, null) as? GradientDrawable
                val background = item.background as? GradientDrawable
                if (background != null && desired != null) {
                    background.color == desired.color
                } else {
                    false
                }
            } else {
                false
            }
        }

fun withBackgroundColorDrawable(colorId: Int) =
        object : BoundedMatcher<View, View>(View::class.java) {

            override fun describeTo(description: Description?) {
                description?.appendText("checking background is $colorId")
            }

            override fun matchesSafely(item: View?) = if (item != null) {
                val desired = ResourcesCompat.getColor(item.resources, colorId, null)
                val background = item.background as? ColorDrawable
                if (background != null ) {
                    background.color == desired
                } else {
                    false
                }
            } else {
                false
            }
        }