package com.desafioamedigital.ui.activity.home

import android.annotation.SuppressLint
import com.desafioamedigital.model.dto.BannerList
import com.desafioamedigital.model.dto.CategoryList
import com.desafioamedigital.model.dto.ProductList
import com.desafioamedigital.model.viewmodel.HomeViewModel
import com.desafioamedigital.service.api.BannerService
import com.desafioamedigital.service.api.CategoryService
import com.desafioamedigital.service.api.ProductService
import com.desafioamedigital.service.di.ConnectionModule
import com.desafioamedigital.service.di.DaggerConnectionComponent
import com.desafioamedigital.util.isNetworkAvailable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomePresenter : HomeContract.Presenter {

    @Inject lateinit var bannerService: BannerService
    @Inject lateinit var categoryService: CategoryService
    @Inject lateinit var productService: ProductService

    private lateinit var view: HomeContract.View

    override fun clearDisposable() {
        CompositeDisposable().clear()
    }

    override fun attachView(view: HomeContract.View) {
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
    override fun getApiResults() {
        view.showProgress()
        Observable.zip(bannerService.getBanners(), categoryService.getCategories(), productService.getBestSellers(),
            Function3<BannerList, CategoryList, ProductList, HomeViewModel> { banners, categories, products ->
                getModel(banners, categories, products)
            }
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.hideProgress()
                view.showHomeData(it)
                view.showContent()
            }, {
                view.hideProgress()
                view.hideContent()
                view.showResponseError(it)
            })
    }

    private fun getModel(banners: BannerList, categories: CategoryList, products: ProductList): HomeViewModel =
        HomeViewModel(banners, categories, products)

}