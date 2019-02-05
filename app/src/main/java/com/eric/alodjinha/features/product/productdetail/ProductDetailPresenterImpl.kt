package com.eric.alodjinha.features.product.productdetail

import com.eric.alodjinha.base.ioThread
import com.eric.alodjinha.features.product.api.ProductInteractorImpl
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class ProductDetailPresenterImpl(val view: ProductDetailView) : ProductDetailPresenter {

    val interactor = ProductInteractorImpl.instance
    val disposable = CompositeDisposable()

    override fun onCreate(productId: Int) {

        view.configureViews()
        getProductDetail(productId)
    }

    override fun onDestroy() {

        disposable.dispose()
    }

    override fun productReservation(productId: Int) {

        interactor.productReservation(productId)
            .ioThread()
            .doOnSubscribe { view.showLoading() }
            .doOnTerminate { view.hideLoading() }
            .subscribe({ view.productReservationSucess(it) }, {

                // TODO
            }).addTo(disposable)
    }

    private fun getProductDetail(productId: Int) {

        interactor.getProductDetail(productId)
            .ioThread()
            .doOnSubscribe { view.showLoading() }
            .doOnTerminate { view.hideLoading() }
            .subscribe({ view.getProductDetail(it) }, {

                // TODO
            }).addTo(disposable)
    }
}