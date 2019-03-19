package com.danilodequeiroz.lodjinha.common.decoration

import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LinePagerDecoration : RecyclerView.ItemDecoration() {

    private val colorActive = Color.parseColor("#ffffff")
    private val colorInactive = Color.parseColor("#80ffffff")
    private val colorTransition = Color.parseColor("#11ffff00")
    private var lastActivePosition = 0

    /**
     * Height of the space the indicator takes up at the bottom of the view.
     */
    private val mIndicatorHeight = (DP * 15).toInt()

    /**
     * Inactive Indicator radiius.
     */
    private val itemRadius = DP * 4
    /**
     * Padding between indicators.
     */
    private val mIndicatorItemPadding = DP * 8

    /**
     * Some more natural animation interpolation
     */
    private val mInterpolator = AccelerateDecelerateInterpolator()

    private val mPaint = Paint()

    init {
        mPaint.strokeCap = Paint.Cap.ROUND
        mPaint.style = Paint.Style.FILL
        mPaint.isAntiAlias = true
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)

        val itemCount = parent.adapter!!.itemCount
        if (itemCount > 1) {

            val totalLength = itemRadius * 2f * itemCount.toFloat()
            val paddingBetweenItems = Math.max(0, itemCount - 1) * mIndicatorItemPadding
            val indicatorTotalWidth = totalLength + paddingBetweenItems
            val indicatorStartX = (parent.width - indicatorTotalWidth) / 2f

            // center vertically in the allotted space
            val indicatorPosY = parent.height - mIndicatorHeight / 2f

            drawInactiveIndicators(c, indicatorStartX, indicatorPosY, itemCount)


            // find active page (which should be highlighted)
            val layoutManager = parent.layoutManager as LinearLayoutManager?
            val activePosition = layoutManager!!.findFirstVisibleItemPosition()
            if (activePosition == RecyclerView.NO_POSITION) {
                return
            }

            // find offset of active page (if the user is scrolling)
            val activeChild = layoutManager.findViewByPosition(activePosition)
            val left = activeChild!!.left
            val width = activeChild.width

            // on swipe the active item will be positioned from [-width, 0]
            // interpolate offset for smooth animation
            val progress = mInterpolator.getInterpolation(left * -1 / width.toFloat())

            drawHighlights(c, indicatorStartX, indicatorPosY, activePosition, progress)
        }
        // center horizontally, calculate width and subtract half from center
    }

    private fun drawInactiveIndicators(c: Canvas, indicatorStartX: Float, cenY: Float, itemCount: Int) {
        mPaint.color = colorInactive

        // width of item indicator including padding
        val itemWidth = itemRadius * 2 + mIndicatorItemPadding

        var cenX = indicatorStartX + itemRadius
        for (i in 0 until itemCount) {
            // draw the circle for every item
            c.drawCircle(cenX, cenY, itemRadius, mPaint)
            cenX += itemWidth
        }
    }

    private fun drawHighlights(c: Canvas, indicatorStartX: Float, indicatorPosY: Float, highlightPosition: Int, progress: Float) {
        var position = highlightPosition
        // width of item indicator including padding
        val itemWidth = itemRadius * 2 + mIndicatorItemPadding
        if (progress == 0f) {
            // no swipe, draw a normal indicator
            mPaint.color = colorActive
            lastActivePosition = position
        } else {
            if (lastActivePosition > position) {
                position = lastActivePosition
            }
            mPaint.color = colorTransition
        }
        val highlightStart = indicatorStartX + itemWidth * position
        c.drawCircle(highlightStart + itemRadius, indicatorPosY, itemRadius, mPaint)
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.bottom = -mIndicatorHeight
    }

    companion object {
        private val DP = Resources.getSystem().displayMetrics.density
    }
}