package com.bryanollivie.lojinha.ui.base

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.blankj.utilcode.util.NetworkUtils
import com.bryanollivie.lojinha.utils.ProgressDialogHelper

abstract class BaseActivity<in V : BaseMvpView, T : BaseMvpPresenter<V>>
    : AppCompatActivity(), BaseMvpView {
    var progress: ProgressDialogHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        progress = ProgressDialogHelper(this)
        presenter.attachView(this as V)

    }

    override fun showLoading() {
        progress!!.show()
    }

    override fun showLoading(message: String) {
        progress!!.show()
    }


    override fun showLoading(title: String, message: String) {
        progress!!.show()
    }

    override fun hideLoading() {
        progress!!.dismiss()
    }

    override fun getContext(): Context = this

    protected open lateinit var presenter: T

    override fun showError(error: String?) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun showError(stringResId: Int) {
        Toast.makeText(this, stringResId, Toast.LENGTH_LONG).show()
    }

    override fun showMessage(srtResId: Int) {
        Toast.makeText(this, srtResId, Toast.LENGTH_LONG).show()
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun isConnected(layout: View, layout_no_connection: View) {
        if (NetworkUtils.isConnected()) {
            layout.visibility = View.VISIBLE
            layout_no_connection.visibility = View.GONE
        } else {
            layout.visibility = View.GONE
            layout_no_connection.visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}
