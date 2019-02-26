package br.com.andrecouto.alodjinha.ui.view

import android.content.Context
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet
import br.com.andrecouto.alodjinha.util.TypefaceUtil

class TypefacedTextView : AppCompatTextView {

    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet? = null) : super(context, attrs) {

        if (!isInEditMode) {
            TypefaceUtil.setTypeface(attrs, this)
        }
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {

        if (!isInEditMode) {
            TypefaceUtil.setTypeface(attrs, this)
        }
    }
}