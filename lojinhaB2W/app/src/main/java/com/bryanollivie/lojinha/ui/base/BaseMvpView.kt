package com.bryanollivie.lojinha.ui.base

import android.content.Context
import android.support.annotation.StringRes

interface BaseMvpView {

    fun getContext(): Context

    fun showLoading()

    fun showLoading(msg: String)

    fun showLoading(title: String, msg: String)

    fun hideLoading()

    fun showError(error: String?)

    fun showError(@StringRes stringResId: Int)

    fun showMessage(@StringRes srtResId: Int)

    fun showMessage(message: String)

}

