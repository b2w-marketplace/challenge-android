package com.example.lidjinha.lodjinha.home

import com.example.lidjinha.lodjinha.model.Banner
import com.example.lidjinha.lodjinha.model.Categorie

interface HomeContract {

    interface View {

        fun setupOfferBanners(banners: List<Banner>)

        fun setupCategories(categories: List<Categorie>)

    }

    interface Presenter {

        fun getBanners()

        fun getCategories()

    }

}