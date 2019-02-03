package com.eric.alodjinha.features.home

import com.eric.alodjinha.features.home.model.Banner
import com.eric.alodjinha.features.home.model.Category
import com.eric.alodjinha.features.product.model.Product

interface HomeFragmentView {

    fun receiveBanner(banners : List<Banner>)
    fun receiveCategories(categories : List<Category>)
    fun receiveProductsMoreSallers(products : List<Product>)
    fun showLoading()
    fun hideLoading()
}