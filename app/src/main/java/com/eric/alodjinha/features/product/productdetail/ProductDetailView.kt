package com.eric.alodjinha.features.product.productdetail

import com.eric.alodjinha.features.product.model.Product

interface ProductDetailView {

    fun productReservationSucess(message: String)
    fun getProductDetail(product : Product)
    fun configureViews()
    fun showLoading()
    fun hideLoading()
}