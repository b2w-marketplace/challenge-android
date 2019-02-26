package marcus.com.br.b2wtest.helper

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.Gravity
import android.widget.LinearLayout
import marcus.com.br.b2wtest.R

class RecyclerIndicator : LinearLayout {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER_HORIZONTAL
    }

    fun attach(recyclerView: RecyclerView) {
        recyclerView.adapter?.let {
            for (i in 0 until it.itemCount)
                addView(inflate(context, R.layout.recycler_indicator, null), i)
        }
    }

    fun setSelected(index: Int) {
        if (index < 0 || index > childCount)
            return

        uncheckAll()
        getChildAt(index).isSelected = true
    }

    private fun uncheckAll() {
        for (i in 0 until childCount)
            getChildAt(i).isSelected = false
    }
}