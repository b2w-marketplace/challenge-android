package com.example.lidjinha.lodjinha.data.usecase

import com.example.lidjinha.lodjinha.data.remote.repository.ProductsRepository
import com.example.lidjinha.lodjinha.model.Product
import kotlin.reflect.KFunction1

class ProductsUseCase : ProductsUseCaseContract {

    override fun getBestSellers(onBestSellersRetrieved: KFunction1<List<Product>, Unit>) {
        ProductsRepository.instance.getBestSellers(onBestSellersRetrieved)
    }
}