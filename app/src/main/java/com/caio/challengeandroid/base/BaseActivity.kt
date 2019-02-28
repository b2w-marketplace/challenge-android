package com.caio.lodjinha.base

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.caio.challengeandroid.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

open class BaseActivity : AppCompatActivity(), CoroutineScope {

    private lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    private val fragmentManager = supportFragmentManager

    fun addFragment(fragment: Fragment) {


        fragmentManager.beginTransaction()
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