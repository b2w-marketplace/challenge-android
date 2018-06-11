package com.example.lidjinha.lodjinha.home

import com.example.lidjinha.lodjinha.model.Banner

interface HomeContract {

    interface View {

        fun setupOfferBanners(banners: List<Banner>)

    }

    interface Presenter {

        fun getBanners()

    }

}