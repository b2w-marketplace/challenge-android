package com.eric.alodjinha.features.product.productdetail

import com.eric.alodjinha.features.product.model.Product

interface ProductDetailPresenter {

    fun onCreate(productId:Int)
    fun onDestroy()
    fun productReservation(productId:Int)
}