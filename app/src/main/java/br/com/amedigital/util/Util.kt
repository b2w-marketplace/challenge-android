package br.com.amedigital.util

fun Double.formatToMoney(): String {
    var decimals = "" + Math.round(this % 1 * 100).toInt()
    while (decimals.length < 2)
        decimals = "0$decimals"

    val integers = "" + this.toInt()
    var compose = ""

    for (i in integers.length - 1 downTo 0)
        compose = integers[i] + (if (compose.isNotEmpty() && compose.length % 3 == 0) "." else "") + compose


    return "$compose,$decimals"
}