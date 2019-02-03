package com.eric.alodjinha.features.product.api

import com.eric.alodjinha.BuildConfig
import com.eric.alodjinha.base.RetrofitService
import io.reactivex.Observable

class ProductRepository {

    val service = RetrofitService(ProductApi::class.java, BuildConfig.URL_BASE)

    fun getProductsByCategory(offset: Int, limite: Int, categoriaId: Int): Observable<ProductResponse> {

        return service.apiService.getProductsByCategory(offset, limite, categoriaId)
    }
}