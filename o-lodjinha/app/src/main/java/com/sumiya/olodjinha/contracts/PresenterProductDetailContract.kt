package com.sumiya.olodjinha.contracts

interface PresenterProductDetailContract {
    fun getProduct(productId: Int)

    fun setProductReservation(productId: Int)
}