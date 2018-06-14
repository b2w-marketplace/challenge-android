package com.example.lidjinha.lodjinha.data.usecase

import com.example.lidjinha.lodjinha.model.Product
import kotlin.reflect.KFunction1

interface ProductsUseCaseContract {

    fun getBestSellers(onBestSellersRetrieved: KFunction1<List<Product>, Unit>)

    fun getReserve(onReserveFinalized: KFunction1<Int, Unit>, productId: kotlin.Int)

    fun getProductsCategory(onProductsRetrieved: KFunction1<List<Product>, Unit>, categoryId: kotlin.Int)

    fun clearProductsCategoryPage()

}