package com.example.lidjinha.lodjinha.data.usecase

import com.example.lidjinha.lodjinha.model.Product
import kotlin.reflect.KFunction1

interface ProductsUseCaseContract {

    fun getBestSellers(onCategoriesRetrieved: KFunction1<List<Product>, Unit>)

    fun getReserve(kFunction1: KFunction1<Int, Unit>, productId: kotlin.Int)

}