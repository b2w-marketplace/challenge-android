package br.com.b2w.lodjinha.views

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.TextView

class StrikeThroughTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : TextView(context, attrs, defStyleAttr) {

    init {
        paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    }

}