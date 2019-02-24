package br.com.prodigosorc.lodjinha.util.extensions

import java.text.DecimalFormat
import java.util.Locale

fun Double.formataParaBrasileiro(prefixo: String): String {
    val formatoBrasileiro = DecimalFormat
            .getCurrencyInstance(Locale("pt", "br"))
    return formatoBrasileiro
            .format(this)
            .replace("R$", "$prefixo R$ ")
}