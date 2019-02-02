package com.eric.alodjinha.features.home

import com.eric.alodjinha.features.home.model.Banner

interface HomeFragmentView {

    fun receiveBanner(banners : List<Banner>)
    fun showLoading()
    fun hideLoading()
}