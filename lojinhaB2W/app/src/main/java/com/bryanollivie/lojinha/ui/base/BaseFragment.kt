package com.bryanollivie.lojinha.ui.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.View
import android.widget.Toast
import com.blankj.utilcode.util.NetworkUtils
import com.bryanollivie.lojinha.R
import com.bryanollivie.lojinha.utils.ProgressDialogHelper

abstract class BaseFragment<in V : BaseMvpView, T : BaseMvpPresenter<V>> : Fragment(), BaseMvpView {

    var progress_fragment: ProgressDialogHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        progress_fragment = ProgressDialogHelper(activity!!)
        presenter.attachView(this as V)
    }

    override fun getContext(): Context = this.activity!!.baseContext

    override fun showLoading() {
        progress_fragment!!.show()
    }

    override fun showLoading(msg: String) {
        progress_fragment!!.show(msg)
    }

    override fun showLoading(title: String, message: String) {
        progress_fragment!!.show()
    }

    override fun hideLoading() {
        progress_fragment!!.dismiss()
    }

    protected abstract var presenter: T

    override fun showError(error: String?) {
        Toast.makeText(activity, error, Toast.LENGTH_LONG).show()
    }

    override fun showError(stringResId: Int) {
        Toast.makeText(activity, stringResId, Toast.LENGTH_LONG).show()
    }

    override fun showMessage(srtResId: Int) {
        Toast.makeText(activity, srtResId, Toast.LENGTH_LONG).show()
    }

    override fun showMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
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

    fun removeAllFragment(replaceFragment: Fragment, tag: String) {

        val manager = activity!!.supportFragmentManager
        val ft = manager.beginTransaction()
        manager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

        ft.replace(R.id.container_body, replaceFragment)
        ft.commitAllowingStateLoss()
    }


}
