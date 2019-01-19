package br.com.cemobile.lodjinha.util

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.support.v7.widget.OrientationHelper
import android.support.v7.widget.RecyclerView
import android.view.View

class StartSnapHelper : LinearSnapHelper() {

    private lateinit var verticalHelper: OrientationHelper
    private lateinit var horizontalHelper: OrientationHelper

    override fun calculateDistanceToFinalSnap(
        layoutManager: RecyclerView.LayoutManager,
        targetView: View
    ): IntArray? {

        val out = IntArray(2)
        if (layoutManager.canScrollHorizontally()) {
            out[0] = distanceToStart(targetView, getHorizontalHelper(layoutManager))
        } else {
            out[0] = 0
        }

        if (layoutManager.canScrollVertically()) {
            out[1] = distanceToStart(targetView, getVerticalHelper(layoutManager))
        } else {
            out[1] = 0
        }

        return out
    }

    override fun findSnapView(layoutManager: RecyclerView.LayoutManager?): View? {
        layoutManager?.let {
            if (it.canScrollHorizontally()) {
                return getStartView(it, getHorizontalHelper(it))
            } else {
                return getStartView(it, getVerticalHelper(it))
            }
        }

        return super.findSnapView(layoutManager)
    }


    private fun getStartView(
        layoutManager: RecyclerView.LayoutManager,
        helper: OrientationHelper
    ) : View? {

        val firstChild = (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

        val lastPosition = layoutManager.getItemCount() - 1
        val isLastItem = layoutManager.findLastCompletelyVisibleItemPosition() == lastPosition

        if (firstChild == RecyclerView.NO_POSITION || isLastItem) {
            return null;
        }

        val child = layoutManager.findViewByPosition(firstChild)

        if (helper.getDecoratedEnd(child) >= helper.getDecoratedMeasurement(child) / 2 &&
            helper.getDecoratedEnd(child) > 0) {
            return child
        } else {
            if (layoutManager.findLastCompletelyVisibleItemPosition() == lastPosition) {
                return null
            } else {
                return layoutManager.findViewByPosition(firstChild + 1)
            }
        }
    }

    private fun distanceToStart(targetView: View, helper: OrientationHelper): Int {
        return helper.getDecoratedStart(targetView) - helper.startAfterPadding
    }

    private fun getVerticalHelper(layoutManager: RecyclerView.LayoutManager): OrientationHelper {
        verticalHelper = OrientationHelper.createVerticalHelper(layoutManager)
        return verticalHelper
    }

    private fun getHorizontalHelper(layoutManager: RecyclerView.LayoutManager): OrientationHelper {
        horizontalHelper = OrientationHelper.createHorizontalHelper(layoutManager)
        return horizontalHelper
    }
}