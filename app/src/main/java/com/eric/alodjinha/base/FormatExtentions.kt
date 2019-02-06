package com.eric.alodjinha.base

import android.graphics.Paint
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.view.View
import android.widget.TextView
import androidx.core.view.setPadding
import kotlinx.android.synthetic.main.product_description_detail.*
import java.text.DecimalFormat

fun Float.formatNumberBr(): String {

    val decimalFormat = DecimalFormat()
    decimalFormat.applyPattern("R$ #,##0.00")
    return decimalFormat.format(this)
}

fun TextView.crossPriceFrom() {

    this.paintFlags = (this.getPaintFlags()
            or Paint.STRIKE_THRU_TEXT_FLAG)
}

fun String.formatFromHTML(): Spanned {

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(this, Html.FROM_HTML_MODE_COMPACT)
    } else {
        return Html.fromHtml(this)
    }

}