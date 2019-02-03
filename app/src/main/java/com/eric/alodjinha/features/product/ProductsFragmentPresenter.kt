package com.eric.alodjinha.features.product

interface ProductsFragmentPresenter {

    fun onCreate(categoryId: Int)
    fun onDestroy()
    fun loadMoreProducts(offset: Int, limite: Int)
}