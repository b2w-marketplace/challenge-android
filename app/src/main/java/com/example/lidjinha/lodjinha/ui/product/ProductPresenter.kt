package com.example.lidjinha.lodjinha.ui.product

import com.example.lidjinha.lodjinha.data.usecase.ProductsUseCase

class ProductPresenter(val view: ProductContract.View, val useCase: ProductsUseCase) : ProductContract.Presenter {

    override fun getReserve(productId: kotlin.Int) {
        useCase.getReserve(this::onReserveFinalized, productId)
    }

    private fun onReserveFinalized(resId: kotlin.Int) {
        view.showReserveMessage(resId)
    }
}