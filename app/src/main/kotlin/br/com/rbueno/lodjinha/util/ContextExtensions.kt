package br.com.rbueno.lodjinha.util

import android.content.Context
import android.content.Intent
import android.net.Uri

fun Context.openUrl(url: String) {
    startActivity(Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(url)
    })
}