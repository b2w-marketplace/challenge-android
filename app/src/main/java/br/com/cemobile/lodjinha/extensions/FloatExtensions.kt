package br.com.cemobile.lodjinha.extensions

import java.text.DecimalFormat
import java.util.*

fun Float.formatToBrazilianCurrency(): String {
    val brazilianFormat = DecimalFormat.getCurrencyInstance(
        Locale(Currency.PORTUGUESE, Currency.BRAZIL)
    )
    return brazilianFormat.format(this)
        .replace(Currency.DEFAULT_FORMAT, Currency.BRAZILIAN_FORMAT_WITH_SPACE)
}

object Currency {

    const val PORTUGUESE = "pt"
    const val BRAZIL = "br"
    const val DEFAULT_FORMAT = "R$"
    const val BRAZILIAN_FORMAT_WITH_SPACE = "R$ "

}