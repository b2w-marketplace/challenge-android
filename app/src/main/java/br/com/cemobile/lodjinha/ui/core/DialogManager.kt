package br.com.cemobile.lodjinha.ui.core

import android.app.Activity
import android.content.DialogInterface
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import br.com.cemobile.lodjinha.R
import org.jetbrains.anko.alert
import org.jetbrains.anko.yesButton

object DialogManager {

    @JvmStatic
    fun showDialog(activity: Activity, message: String) {
        activity.alert(message) { yesButton(DialogInterface::dismiss) }
            .show().apply {
                getButton(AlertDialog.BUTTON_POSITIVE)?.setTextColor(
                        ContextCompat.getColor(activity, R.color.colorPrimary)
                )
            }
    }

}