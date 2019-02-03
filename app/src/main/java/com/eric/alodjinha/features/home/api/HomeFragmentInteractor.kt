package com.eric.alodjinha.features.home.api

import com.eric.alodjinha.base.ApiResponse
import io.reactivex.Observable

interface HomeFragmentInteractor {

    fun getBanners() : Observable<ApiResponse<BannerResponse>>
    fun getCategories() : Observable<CategoriesResponse>
    fun getProductsMoreSallers() : Observable<ProductResponse>
}