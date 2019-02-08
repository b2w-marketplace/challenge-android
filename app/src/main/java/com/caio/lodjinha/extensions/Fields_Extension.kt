package com.caio.lodjinha.extensions

import android.graphics.Paint
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.widget.TextView
import java.text.DecimalFormat

fun Float.formatNumberReal(): String {

    val decimalFormat = DecimalFormat()
    decimalFormat.applyPattern("R$ #,##0.00")
    return decimalFormat.format(this)
}

fun TextView.priceFrom() {

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