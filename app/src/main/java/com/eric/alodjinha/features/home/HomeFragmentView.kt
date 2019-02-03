package com.eric.alodjinha.features.home

import com.eric.alodjinha.features.home.model.Banner
import com.eric.alodjinha.features.home.model.Category

interface HomeFragmentView {

    fun receiveBanner(banners : List<Banner>)
    fun receiveCategories(categories : List<Category>)
    fun showLoading()
    fun hideLoading()
}