package br.com.andreguedes.alodjinha.ui.main

class MainPresenter(val view: MainContract.View) : MainContract.Presenter {

    override fun initHomeFragment() {
        view.initHomeFragment()
    }

}