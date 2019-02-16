package com.bryanollivie.lojinha.ui.base

open class BasePresenter<V : BaseMvpView> : BaseMvpPresenter<V> {

    private var mView: V? = null

    private val isViewAttached: Boolean get() = mView != null

    override fun attachView(view: V) {
        mView = view
    }

    fun getView(): V? {
        return mView
    }

    override fun detachView() {
        mView = null
    }


}
