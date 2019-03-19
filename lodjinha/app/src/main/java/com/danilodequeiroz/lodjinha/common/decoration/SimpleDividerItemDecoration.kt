package com.danilodequeiroz.lodjinha.common.decoration

import androidx.recyclerview.widget.RecyclerView
import android.content.Context
import android.graphics.Canvas
import androidx.core.content.ContextCompat
import com.danilodequeiroz.lodjinha.R


class SimpleDividerItemDecoration(context: Context) : RecyclerView.ItemDecoration() {
    private var divider = ContextCompat.getDrawable(context, R.drawable.divider_line)


    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = child.bottom + params.bottomMargin
            val intrinsicHeight = divider?.intrinsicHeight ?: 0
            val bottom = top + intrinsicHeight

            divider?.setBounds(left, top, right, bottom)
            divider?.draw(canvas)
        }
    }
}