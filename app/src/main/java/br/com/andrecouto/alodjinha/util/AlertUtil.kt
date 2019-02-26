package br.com.andrecouto.alodjinha.util

import android.app.AlertDialog
import android.content.Context
import br.com.andrecouto.alodjinha.R

object AlertUtil {

    fun alertDialogShow(context: Context, message: String) {
        val alertDialog = AlertDialog.Builder(context,  R.style.AlertDialogTheme).create()
        alertDialog.setMessage(message)
        alertDialog.setButton("OK", { dialog, which -> alertDialog.dismiss() })
        alertDialog.show()
    }
}