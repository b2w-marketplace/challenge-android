package br.com.andrecouto.alodjinha.ui.view

import android.content.Context
import android.view.MotionEvent
import android.view.GestureDetector
import android.support.v4.view.ViewPager
import android.util.AttributeSet

class HorizontalViewPager : ViewPager {
    lateinit var xScrollDetector: GestureDetector

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        xScrollDetector = GestureDetector(getContext(), XScrollDetector())
    }

    internal inner class XScrollDetector : GestureDetector.SimpleOnGestureListener() {
        override fun onScroll(e1: MotionEvent, e2: MotionEvent, distanceX: Float, distanceY: Float): Boolean {
            return Math.abs(distanceX) > Math.abs(distanceY)
        }
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        if (xScrollDetector.onTouchEvent(ev)) {
            super.onInterceptTouchEvent(ev)
            return true
        }

        return super.onInterceptTouchEvent(ev)
    }

}