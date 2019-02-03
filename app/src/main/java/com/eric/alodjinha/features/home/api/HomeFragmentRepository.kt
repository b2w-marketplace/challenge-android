package com.eric.alodjinha.features.home.api

import com.eric.alodjinha.BuildConfig
import com.eric.alodjinha.base.RetrofitService
import com.eric.alodjinha.features.product.api.ProductResponse
import io.reactivex.Observable

class HomeFragmentRepository {

    val service = RetrofitService(HomeApi::class.java, BuildConfig.URL_BASE)

    fun getBanner(): Observable<BannerResponse> {

        return service.apiService.getBanner()
    }

    fun getCategories(): Observable<CategoriesResponse> {

        return service.apiService.getCategories()
    }

    fun getProductsMoreSallers(): Observable<ProductResponse>{

        return service.apiService.getProductsMoreSallers()
    }
}