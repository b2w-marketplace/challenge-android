package com.example.lidjinha.lodjinha.ui.product

interface ProductContract {

    interface View {

        fun showReserveMessage(text: Int)

    }

    interface Presenter {

        fun getReserve(productId: kotlin.Int)

    }

}