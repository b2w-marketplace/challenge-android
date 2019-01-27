package com.sumiya.olodjinha.contracts

import com.sumiya.olodjinha.contracts.base.ViewBaseContract
import com.sumiya.olodjinha.model.ProductModel

interface ViewProductDetailContract : ViewBaseContract {
    fun setProductResponse(product: ProductModel)
    fun showReservationResponse()
}