package com.eric.alodjinha.features.product

import com.eric.alodjinha.base.ioThread
import com.eric.alodjinha.features.product.api.ProductInteractorImpl
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class ProductsFragmentPresenterImpl(val view: ProductsFragmentView) : ProductsFragmentPresenter {

    val interactor = ProductInteractorImpl.instance
    val disposable = CompositeDisposable()

    override fun onCreate(categoriaId: Int) {

        view.configureViews()
        getProductsByCategory(0, 20, categoriaId)
    }

    override fun onDestroy() {

        disposable.dispose()
    }

    private fun getProductsByCategory(offset: Int, limite: Int, categoriaId: Int) {

        interactor.getProductsByCategory(offset, limite, categoriaId)
            .ioThread()
            .doOnSubscribe { view.showLoading() }
            .doOnTerminate { view.hideLoading() }
            .subscribe({ view.receiveProducts(it.data) }, {

                // TODO
            }).addTo(disposable)
    }
}