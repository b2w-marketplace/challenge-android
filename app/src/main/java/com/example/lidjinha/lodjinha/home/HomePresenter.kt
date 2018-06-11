package com.example.lidjinha.lodjinha.home

import com.example.lidjinha.lodjinha.data.usecase.BannerUseCaseContract
import com.example.lidjinha.lodjinha.model.Banner

class HomePresenter(val view: HomeContract.View, val useCase: BannerUseCaseContract) : HomeContract.Presenter {

    init {
        getBanners()
    }

    override fun getBanners() {
        useCase.getBanners(this::onBannersRetrieved)
    }

    private fun onBannersRetrieved(banners: List<Banner>?) {
        banners?.apply { view.setupOfferBanners(this) }

    }
}