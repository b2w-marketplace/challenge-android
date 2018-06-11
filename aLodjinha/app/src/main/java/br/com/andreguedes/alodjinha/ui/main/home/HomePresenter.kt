package br.com.andreguedes.alodjinha.ui.main.home

import android.util.Log
import br.com.andreguedes.alodjinha.data.source.ALodjinhaRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomePresenter(
        var view: HomeContract.View
) : HomeContract.Presenter {

    private val TAG = HomePresenter::class.java.name

    private val repository = ALodjinhaRepository()

    override fun subscribe() {
        getBanners()
        getCategories()
        getProductsBestSellers()
    }

    override fun getBanners() {
        addDisposable(repository.getBanners()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({
                    view.setBanners(it.bannerList!!)
                }, {
                    Log.e(TAG, it.message)
                }))
    }

    override fun getCategories() {

    }

    override fun getProductsBestSellers() {

    }

}