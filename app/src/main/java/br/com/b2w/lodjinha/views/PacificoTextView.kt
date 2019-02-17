package br.com.b2w.lodjinha.views

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView

class PacificoTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : TextView(context, attrs, defStyleAttr) {

    init {
        val font = Typeface.createFromAsset(context.assets, "fonts/Pacifico-Regular.ttf")
        setTypeface(font, Typeface.NORMAL)
    }

}