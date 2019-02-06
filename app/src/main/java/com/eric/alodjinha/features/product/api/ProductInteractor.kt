package com.eric.alodjinha.features.product.api

import com.eric.alodjinha.features.product.model.Product
import io.reactivex.Observable

interface ProductInteractor {

    fun getProductsByCategory(offset: Int, limite: Int, categoriaId: Int) : Observable<ProductResponse>
    fun productReservation(productId: Int): Observable<ProductReservationResponse>
    fun getProductDetail(productId: Int): Observable<Product>
}