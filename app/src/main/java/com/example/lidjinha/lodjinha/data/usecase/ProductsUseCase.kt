package com.example.lidjinha.lodjinha.data.usecase

import com.example.lidjinha.lodjinha.data.remote.repository.ProductsRepository
import com.example.lidjinha.lodjinha.model.Product
import kotlin.reflect.KFunction1

class ProductsUseCase : ProductsUseCaseContract {

    private var productsCategoryPage = 1

    override fun getBestSellers(onBestSellersRetrieved: KFunction1<List<Product>, Unit>) {
        ProductsRepository.instance.getBestSellers(onBestSellersRetrieved)
    }

    override fun getReserve(onReserveFinalized: KFunction1<Int, Unit>, productId: kotlin.Int) {
        ProductsRepository.instance.getReserve(onReserveFinalized, productId)
    }

    override fun getProductsCategory(onProductsRetrieved: KFunction1<List<Product>, Unit>, categoryId: kotlin.Int) {
        ProductsRepository.instance.getProductsCategory(onProductsRetrieved, categoryId, productsCategoryPage)
        productsCategoryPage++
    }

    override fun clearProductsCategoryPage() {
        productsCategoryPage = 1
    }

}