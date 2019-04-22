package com.desafioamedigital.ui.base

import android.content.Context
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.Toolbar

class BaseContract {

    interface BaseView {
        fun configureToolbar(title: String)
        fun configureToolbar(title: String, toolbar: Toolbar)
        fun configureToolbar(toolbar: Toolbar)
        fun configureDrawer(drawer: DrawerLayout, toolbar: Toolbar, nv_menu: NavigationView)
        fun getContext(): Context
        fun showProgress()
        fun hideProgress()
        fun showAlertDialog(title: String, message: String, buttonText: String)
        fun showResponseError(error: Throwable)
    }

    interface BasePresenter<in T>{
        fun clearDisposable()
        fun attachView(view : T)
    }

}