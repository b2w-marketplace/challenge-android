package com.eric.alodjinha

class MainActivityPresenterImpl(val view : MainActivityView): MainActivityPresenter  {

    override fun onCreate() {

        view.configureViews()
    }
}