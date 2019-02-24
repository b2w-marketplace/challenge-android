package br.com.prodigosorc.lodjinha.util.extensions

import android.graphics.Paint
import android.widget.TextView

fun TextView.strike(): CharSequence? {
    this.paintFlags = this.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    return this.text
}

fun limitarTitulos(text: String, maxSizeLine: Int): String {
    val substring = StringBuilder()
    if (text.length <= maxSizeLine) {
        return text
    } else {
        val palavras = text.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        var charCount = 0
        for (palavra in palavras) {
            val wordPlusSpace = palavra.length + 1
            charCount += wordPlusSpace
            if (charCount > maxSizeLine) {
                break
            } else {
                substring.append(if (substring.length != 0) " " else "")
            }
            substring.append(palavra)
        }
        return substring.toString().replace(",", "")
    }
}
