package com.eric.alodjinha.features.product.api

import io.reactivex.Observable

interface ProductInteractor {

    fun getProductsByCategory(offset: Int, limite: Int, categoriaId: Int) : Observable<ProductResponse>
}