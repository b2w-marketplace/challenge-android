package com.example.lidjinha.lodjinha.home

import com.example.lidjinha.lodjinha.data.usecase.BannerUseCaseContract
import com.example.lidjinha.lodjinha.data.usecase.CategoriesUseCase
import com.example.lidjinha.lodjinha.model.Banner
import com.example.lidjinha.lodjinha.model.Categorie

class HomePresenter(private val view: HomeContract.View,
                    private val bannerUseCase: BannerUseCaseContract,
                    private val categoriesUseCase: CategoriesUseCase
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
}
