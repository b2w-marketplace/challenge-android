package com.example.lidjinha.lodjinha.ui.home

import com.example.lidjinha.lodjinha.data.usecase.*
import com.example.lidjinha.lodjinha.model.Banner
import com.example.lidjinha.lodjinha.model.Categorie
import com.example.lidjinha.lodjinha.model.Product

class HomePresenter(private val view: HomeContract.View,
                    private val bannerUseCase: BannerUseCaseContract,
                    private val categoriesUseCase: CategoriesUseCaseContract,
                    private val productsUseCase: ProductsUseCaseContract
) : HomeContract.Presenter {

    override fun getBanners() {
        bannerUseCase.getBanners(this::onBannersRetrieved)
    }

    private fun onBannersRetrieved(banners: List<Banner>?) {
        banners?.apply { view.setupOfferBanners(this) }
    }

    override fun getCategories() {
        categoriesUseCase.getCategories(this::onCategoriesRetrieved)
    }

    private fun onCategoriesRetrieved(categories: List<Categorie>) {
        view.setupCategories(categories)
    }

    override fun getBestSellers() {
        productsUseCase.getBestSellers(this::onBestSellersRetrieved)
        view.showProgress()
    }

    private fun onBestSellersRetrieved(bestSellers: List<Product>) {
        view.setupBestSellers(bestSellers)
        view.hideProgress()
    }
}
