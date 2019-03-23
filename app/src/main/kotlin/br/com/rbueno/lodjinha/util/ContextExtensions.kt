package br.com.rbueno.lodjinha.util

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri

fun Context.openUrl(url: String) {
    startActivity(Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(url)
    })
}

fun Context.showAlert(message: Int, positiveButton: Int, listener: (dialog: DialogInterface, which: Int) -> Unit) {
    AlertDialog.Builder(this)
        .setMessage(message)
        .setPositiveButton(positiveButton, listener)
        .create()
        .show()
}