package com.caio.lodjinha.base

import android.app.AlertDialog
import android.content.DialogInterface
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.caio.lodjinha.R
import com.caio.lodjinha.di.ApplicationBase

open class BaseActivity : AppCompatActivity() {

    init {
        ApplicationBase.activityContext = this
    }

    fun addFragment(fragment: Fragment) {

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.content, fragment, fragment.javaClass.simpleName)
            .setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out)
            .addToBackStack(null)
            .commit()
    }

    fun showDialogWithCallback(title: String, message: String, positiveButton: String, negativeButton: String?,
                               positiveListener: DialogInterface.OnClickListener,
                               negativeListener: DialogInterface.OnClickListener?) {

        showDialogWithCallback(title, message, positiveButton, negativeButton, positiveListener, negativeListener, null, null)
    }

    fun showDialogWithCallback(title: String, message: String, positiveButton: String, negativeButton: String?,
                               positiveListener: DialogInterface.OnClickListener,
                               negativeListener: DialogInterface.OnClickListener?,
                               positiveColor: Int?,
                               negativeColor: Int?) {


        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
            .setMessage(message)

        builder.setPositiveButton(positiveButton, positiveListener)
        if (negativeListener == null) {
            builder.setNegativeButton(negativeButton) { _, _ ->
            }
        } else {
            builder.setNegativeButton(negativeButton, negativeListener)
        }
        builder.setCancelable(false)


        val alert = builder.create()
        alert.show()

        if (alert.getButton(AlertDialog.BUTTON_POSITIVE) != null && positiveColor != null) {
            alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(resources.getColor(positiveColor))
        }
        if (alert.getButton(AlertDialog.BUTTON_NEGATIVE) != null && negativeColor != null) {
            alert.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(resources.getColor(negativeColor))
        }
    }
}