package com.example.lidjinha.lodjinha.ui.productscategory

import com.example.lidjinha.lodjinha.model.Product

interface ProductsCategoryContract {

    interface View {

        fun setupComponentsView(products: List<Product>)

        fun showProgress()

        fun hideProgress()

        fun showProgressBar()

    }

    interface Presenter {

        fun getProducts(categoryId: kotlin.Int, isNewValues: Boolean)

        fun clearPages()

    }
}