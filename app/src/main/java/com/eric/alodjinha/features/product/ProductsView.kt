package com.eric.alodjinha.features.product

import com.eric.alodjinha.features.product.model.Product

interface ProductsView {

    fun receiveProducts(products: List<Product>)
    fun configureViews()
    fun showLoading()
    fun hideLoading()
}