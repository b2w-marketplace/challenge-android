package br.com.dafle.alodjinha.util

import java.util.regex.Matcher
import java.util.regex.Pattern

class Utils {

    companion object {

        @JvmStatic
        fun isValidEmail(target: CharSequence?): Boolean {
            val pattern: Pattern = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
            val matcher: Matcher
            matcher = pattern.matcher(target)
            return matcher.matches()
        }
    }
}