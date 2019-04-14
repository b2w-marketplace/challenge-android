package br.com.dafle.alodjinha.matcher

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

fun withDrawable(drawableId: Int) = object : TypeSafeMatcher<View>(View::class.java) {

    override fun describeTo(description: Description?) {
        description?.appendText("checking Drawable is $drawableId")
    }

    override fun matchesSafely(item: View?) = if (item != null) {
        if(item !is ImageView) {

        }

        val imageView: ImageView? = item as ImageView
        val drawable = item.resources.getDrawable(drawableId, null)

        val bitmap = getBitmap(drawable)
        if(imageView != null) {
            val otherBitmap = getBitmap(imageView.drawable)
            bitmap.sameAs(otherBitmap)
        } else {
            false
        }

    } else {
        false
    }
}

private fun getBitmap(drawable: Drawable): Bitmap {
    val bitmap = Bitmap.createBitmap(
        drawable.intrinsicWidth,
        drawable.intrinsicHeight, Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight())
    drawable.draw(canvas)
    return bitmap
}