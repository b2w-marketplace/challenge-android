package br.com.andrecouto.alodjinha.ui.view

import android.content.Context
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.util.AttributeSet

class CustomAppBarLayout : AppBarLayout, AppBarLayout.OnOffsetChangedListener {

    private var state: State? = null
    private var onStateChangeListener: OnStateChangeListener? = null

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (layoutParams !is CoordinatorLayout.LayoutParams || parent !is CoordinatorLayout) {
            throw IllegalStateException(
                    "MyAppBarLayout must be a direct child of CoordinatorLayout.")
        }
        addOnOffsetChangedListener(this)
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        if (verticalOffset == 0) {
            if (onStateChangeListener != null && state != State.EXPANDED) {
                onStateChangeListener!!.onStateChange(State.EXPANDED)
            }
            state = State.EXPANDED
        } else if (Math.abs(verticalOffset) >= appBarLayout.totalScrollRange) {
            if (onStateChangeListener != null && state != State.COLLAPSED) {
                onStateChangeListener!!.onStateChange(State.COLLAPSED)
            }
            state = State.COLLAPSED
        } else {
            if (onStateChangeListener != null && state != State.IDLE) {
                onStateChangeListener!!.onStateChange(State.IDLE)
            }
            state = State.IDLE
        }
    }

    fun setOnStateChangeListener(listener: OnStateChangeListener) {
        this.onStateChangeListener = listener
    }

    interface OnStateChangeListener {
        fun onStateChange(toolbarChange: State)
    }

    enum class State {
        COLLAPSED,
        EXPANDED,
        IDLE
    }
}