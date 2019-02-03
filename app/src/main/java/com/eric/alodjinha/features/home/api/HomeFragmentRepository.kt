package com.eric.alodjinha.features.home.api

import com.eric.alodjinha.BuildConfig
import com.eric.alodjinha.base.ApiResponse
import com.eric.alodjinha.base.RetrofitService
import io.reactivex.Observable

class HomeFragmentRepository {

    val service = RetrofitService(HomeApi::class.java, BuildConfig.URL_BASE)

    fun getBanner(): Observable<ApiResponse<BannerResponse>> {

        return service.apiService.getBanner()
    }

    fun getCategories(): Observable<ApiResponse<CategoriesResponse>> {

        return service.apiService.getCategories()
    }

    fun getProductsMoreSallers(): Observable<ApiResponse<ProductResponse>>{

        return service.apiService.getProductsMoreSallers()
    }
}