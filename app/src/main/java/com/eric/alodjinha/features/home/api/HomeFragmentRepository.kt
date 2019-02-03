package com.eric.alodjinha.features.home.api

import com.eric.alodjinha.BuildConfig
import com.eric.alodjinha.base.RetrofitService
import io.reactivex.Observable

class HomeFragmentRepository {

    val service = RetrofitService(ProductApi::class.java, BuildConfig.URL_BASE)

    fun getBanner(): Observable<BannerResponse> {

        return service.apiService.getBanner()
    }

    fun getCategories(): Observable<CategoriesResponse> {

        return service.apiService.getCategories()
    }
}