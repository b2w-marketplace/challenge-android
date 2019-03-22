package br.com.rbueno.lodjinha.util

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*


private const val MONETARY_PATTERN = "[^\\d]"
private const val FIRST_ZEROS_PATTERN = "^0*"
private const val DECIMAL_DIVISION_FACTOR = 100
private const val DEFAULT_STRING_VALUE = ""
private const val DEFAULT_DECIMAL_VALUE = 0.0

private const val DEFAULT_LANGUAGE = "pt"
private const val DEFAULT_COUNTRY = "BR"
private const val MIN_FRACTION_DIGITS = 2
private const val MAX_FRACTION_DIGITS = 2
private const val POSITIVE_PREFIX = "R$ "
private const val NEGATIVE_PREFIX = "R$ -"

fun Double.toMoneyDisplay(): String =
    (NumberFormat.getCurrencyInstance(Locale(DEFAULT_LANGUAGE, DEFAULT_COUNTRY)) as DecimalFormat).apply {
        maximumFractionDigits = MAX_FRACTION_DIGITS
        minimumFractionDigits = MIN_FRACTION_DIGITS
    }.format(this)