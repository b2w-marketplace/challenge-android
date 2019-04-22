package com.desafioamedigital.ui.activity.product_details

import android.annotation.SuppressLint
import com.desafioamedigital.R
import com.desafioamedigital.service.api.ProductService
import com.desafioamedigital.service.di.ConnectionModule
import com.desafioamedigital.service.di.DaggerConnectionComponent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProductDetailsPresenter: ProductDetailsContract.Presenter {

    @Inject lateinit var productService: ProductService

    private lateinit var view: ProductDetailsContract.View

    override fun clearDisposable() {
        CompositeDisposable().clear()
    }

    override fun attachView(view: ProductDetailsContract.View) {
        this.view = view
        injectPresenter()
    }

    private fun injectPresenter(){
        DaggerConnectionComponent.builder()
            .connectionModule(ConnectionModule())
            .build()
            .inject(this)
    }

    @SuppressLint("CheckResult")
    override fun reserveProduct(productId: Int) {
        val res = view.getContext().resources
        view.showProgress()
        productService.getProduct(productId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                view.hideProgress()
                view.showAlertDialog(
                    "",
                    res.getString(R.string.product_details_activity_alert_success_reservation),
                    res.getString(R.string.product_details_activity_alert_button_text)
                )
            },{
                view.hideProgress()
                view.showResponseError(it)
            })
    }

}