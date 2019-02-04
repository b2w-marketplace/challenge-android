package com.eric.alodjinha.base

import java.text.DecimalFormat

fun Float.formatNumberBr() : String{

    val decimalFormat = DecimalFormat()
    decimalFormat.applyPattern("R$ #,##0.00")
    return decimalFormat.format(this)
}