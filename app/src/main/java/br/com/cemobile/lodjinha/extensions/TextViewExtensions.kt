package br.com.cemobile.lodjinha.extensions

import android.text.Spannable
import android.text.Spanned
import android.text.style.StrikethroughSpan
import android.widget.TextView

fun TextView.setTextWithStrikeThrough(text: String) {
    this.setText(text, TextView.BufferType.SPANNABLE)
    val spannable = this.text as Spannable
    spannable.setSpan(StrikethroughSpan(), 0, text.length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
}