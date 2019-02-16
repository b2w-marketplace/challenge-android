package com.bryanollivie.lojinha.ui.base

interface BaseMvpPresenter<in V : BaseMvpView> {

    fun attachView(view: V)

    fun detachView()

}
