package br.com.dafle.alodjinha.util

import java.text.NumberFormat
import java.util.*

fun Double.toCoin(locale: Locale = Locale("pt", "BR")): String {
    return NumberFormat.getCurrencyInstance(locale).format(this)
}