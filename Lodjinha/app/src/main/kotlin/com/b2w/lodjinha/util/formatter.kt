package com.b2w.lodjinha.util

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*


fun Int.toCurrency(currencyType: String = "") = currencyType + String.format("%,d", this).replace(",", ".")

fun Double.toCurrency(currencyType: String = "R$ ", showDecimals: Boolean = true): String {
    val formatter = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
    val decimalFormatSymbols = (formatter as DecimalFormat).decimalFormatSymbols

    decimalFormatSymbols.currencySymbol = currencyType
    if (!showDecimals) {
        formatter.maximumFractionDigits = 0
    }

    formatter.decimalFormatSymbols = decimalFormatSymbols
    return formatter.format(this)
}
