package br.com.rbueno.lodjinha.util

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

private const val DEFAULT_LANGUAGE = "pt"
private const val DEFAULT_COUNTRY = "BR"
private const val MIN_FRACTION_DIGITS = 2
private const val MAX_FRACTION_DIGITS = 2

fun Double.toMoneyDisplay(): String =
    (NumberFormat.getCurrencyInstance(Locale(DEFAULT_LANGUAGE, DEFAULT_COUNTRY)) as DecimalFormat).apply {
        maximumFractionDigits = MAX_FRACTION_DIGITS
        minimumFractionDigits = MIN_FRACTION_DIGITS
    }.format(this)