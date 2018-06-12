package com.example.lidjinha.lodjinha.ui.product

interface ProductContract {

    interface View {

        fun showReserveMessage(text: Int)

        fun showProgress()

        fun hideProgress()

    }

    interface Presenter {

        fun getReserve(productId: kotlin.Int)

    }

}