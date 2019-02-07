package com.eric.alodjinha.features.product

interface ProductsPresenter {

    fun onCreate(categoryId: Int)
    fun onDestroy()
    fun loadMoreProducts(offset: Int, limite: Int)
    fun configureEmptyList()
}