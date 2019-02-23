package marcus.com.br.b2wtest.helper.snap

import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
import java.text.NumberFormat

fun PagerSnapHelper.getSnapPosition(recyclerView: RecyclerView): Int {
    val layoutManager = recyclerView.layoutManager ?: return RecyclerView.NO_POSITION
    val snapView = findSnapView(layoutManager) ?: return RecyclerView.NO_POSITION
    return layoutManager.getPosition(snapView)
}

fun RecyclerView.attachSnapHelperWithListener(
    snapHelper: PagerSnapHelper,
    onSnapPositionChangeListener: OnSnapPositionChangeListener,
    behavior: SnapOnScrollListener.Behavior = SnapOnScrollListener.Behavior.NOTIFY_ON_SCROLL
) {
    snapHelper.attachToRecyclerView(this)
    val snapOnScrollListener = SnapOnScrollListener(snapHelper, behavior, onSnapPositionChangeListener)
    addOnScrollListener(snapOnScrollListener)
}

fun String.toBRL(): String {
    return NumberFormat.getCurrencyInstance().format(this
            .replace(",", ".").toFloat())
            .replace("$", " ")
            .replace(".", ",")
}

fun Double.toBRL(): String {
    return "%.2f".format(this).replace(".", ",")
}

interface OnSnapPositionChangeListener {

    fun onSnapPositionChange(position: Int)
}