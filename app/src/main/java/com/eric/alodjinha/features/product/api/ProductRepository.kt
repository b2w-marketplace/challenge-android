package com.eric.alodjinha.features.product.api

import com.eric.alodjinha.BuildConfig
import com.eric.alodjinha.base.RetrofitService
import com.eric.alodjinha.features.product.model.Product
import io.reactivex.Observable

class ProductRepository {

    val service = RetrofitService(ProductApi::class.java, BuildConfig.URL_BASE)

    fun getProductsByCategory(offset: Int, limite: Int, categoriaId: Int): Observable<ProductResponse> {

        return service.apiService.getProductsByCategory(offset, limite, categoriaId)
    }

    fun productReservation(productId: Int): Observable<String> {

        return service.apiService.productReservation(productId)
    }

    fun productDetail(productId: Int): Observable<Product> {

        return service.apiService.getProductDetail(productId)
    }
}