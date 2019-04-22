package com.desafioamedigital.ui.activity.category

import android.annotation.SuppressLint
import com.desafioamedigital.service.api.ProductService
import com.desafioamedigital.service.di.ConnectionModule
import com.desafioamedigital.service.di.DaggerConnectionComponent
import com.desafioamedigital.util.ErrorTypes
import com.desafioamedigital.util.isNetworkAvailable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import javax.inject.Inject

class CategoryPresenter : CategoryContract.Presenter {

    @Inject lateinit var productService: ProductService

    private lateinit var view: CategoryContract.View

    override fun clearDisposable() {
        CompositeDisposable().clear()
    }

    override fun attachView(view: CategoryContract.View) {
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
    override fun getProducts(categoryId: Int, offset: Int, limit: Int) {
        if (offset == 0) {
            view.showProgress()
        } else {
            view.showBottomProgress()
        }
        productService.getProductsByCategory(categoryId, offset, limit)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (offset == 0) {
                    view.hideProgress()
                    if (it.productList.isEmpty()) {
                        view.showEmptyView()
                    } else {
                        view.listProducts(it.productList)
                    }
                } else {
                    view.hideBottomProgress()
                    view.addProducts(it.productList)
                }
            }, {
                view.hideProgress()
                view.hideBottomProgress()
                view.showResponseError(it)
            })

    }
}