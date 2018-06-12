package br.com.andreguedes.alodjinha.helper

import java.text.NumberFormat
import java.util.*

object StringHelper {

    fun formatCurrencyNew(value: Double): String {
        return NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(value)
    }

}