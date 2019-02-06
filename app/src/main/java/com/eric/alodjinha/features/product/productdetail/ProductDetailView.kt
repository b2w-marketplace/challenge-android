package com.eric.alodjinha.features.product.productdetail

import com.eric.alodjinha.features.product.api.ProductReservationResponse
import com.eric.alodjinha.features.product.model.Product

interface ProductDetailView {

    fun productReservationSucess(message: ProductReservationResponse)
    fun productReservationError()
    fun getProductDetail(product : Product)
    fun configureViews()
    fun showLoading()
    fun hideLoading()
}