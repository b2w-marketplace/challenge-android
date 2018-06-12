package com.example.lidjinha.lodjinha.ui.home

import com.example.lidjinha.lodjinha.model.Banner
import com.example.lidjinha.lodjinha.model.Categorie
import com.example.lidjinha.lodjinha.model.Product

interface HomeContract {

    interface View {

        fun setupOfferBanners(banners: List<Banner>)

        fun setupCategories(categories: List<Categorie>)

        fun setupBestSellers(bestSellers: List<Product>)

        fun showProgress()

        fun hideProgress()

    }

    interface Presenter {

        fun getBanners()

        fun getCategories()

        fun getBestSellers()

    }

}