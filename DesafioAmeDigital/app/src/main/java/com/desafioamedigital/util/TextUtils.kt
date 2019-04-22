package com.desafioamedigital.util

import android.graphics.Paint
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.widget.TextView
import java.text.DecimalFormat

fun String.convertedTextFromHtml(): Spanned{
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(this)
    }
}

fun Double.priceDecimalFormat(): String{
    val format = DecimalFormat()
    format.applyPattern("#.00")
    return format.format(this)
}

fun TextView.setStrikeThrough(){
    this.paintFlags = this.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
}