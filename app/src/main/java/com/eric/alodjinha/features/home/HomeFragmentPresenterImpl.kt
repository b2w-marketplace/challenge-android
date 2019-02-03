package com.eric.alodjinha.features.home

import android.util.Log
import com.eric.alodjinha.base.ioThread
import com.eric.alodjinha.features.home.api.HomeFragmentInteractor
import com.eric.alodjinha.features.home.api.HomeFragmentInteractorImpl
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class HomeFragmentPresenterImpl(val view: HomeFragmentView) : HomeFragmentPresenter {

    val fragmentInteractor: HomeFragmentInteractor = HomeFragmentInteractorImpl.instance
    val disposible = CompositeDisposable()

    override fun onCreate() {

        getBanners()
        getCategories()
    }

    override fun onDestroy() {

        disposible.dispose()
    }

    private fun getBanners() {

        fragmentInteractor.getBanners()
            .ioThread()
            .doOnSubscribe { view.showLoading() }
            .doOnTerminate { view.hideLoading() }
            .subscribe({
                view.receiveBanner(it.data!!.data)
            },
                {
                    Log.e("HomePresenter", it.message)
                }).addTo(disposible)
    }

    private fun getCategories() {

        fragmentInteractor.getCategories()
            .ioThread()
            .doOnSubscribe { view.showLoading() }
            .doOnTerminate { view.hideLoading() }
            .subscribe({
                view.receiveCategories(it.data)
            },
                {
                    Log.e("HomePresenter", it.message)
                }).addTo(disposible)

        fragmentInteractor.getCategories()
            .ioThread()
            .doOnSubscribe { view.showLoading() }
            .doOnTerminate { view.hideLoading() }
            .subscribe({
                view.receiveCategories(it.data)
            },
                {
                    Log.e("HomePresenter", it.message)
                }).addTo(disposible)
    }

    private fun getProductsMoreSallers() {

        fragmentInteractor.getProductsMoreSallers()
            .ioThread()
            .doOnSubscribe { view.showLoading() }
            .doOnTerminate { view.hideLoading() }
            .subscribe({
                view.receiveProductsMoreSallers(it.data)
            },
                {
                    Log.e("HomePresenter", it.message)
                }).addTo(disposible)
    }
}