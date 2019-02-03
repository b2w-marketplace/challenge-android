package com.eric.alodjinha.features.product.api

import io.reactivex.Observable

class ProductInteractorImpl : ProductInteractor {

    val repository = ProductRepository()

    companion object {

        val instance: ProductInteractor = ProductInteractorImpl()
    }

    override fun getProductsByCategory(offset: Int, limite: Int, categoriaId: Int): Observable<ProductResponse> {

        return repository.getProductsByCategory(offset, limite, categoriaId).map { it }
    }
}