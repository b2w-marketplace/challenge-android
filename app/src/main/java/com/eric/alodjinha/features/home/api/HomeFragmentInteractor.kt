package com.eric.alodjinha.features.home.api

import io.reactivex.Observable

interface HomeFragmentInteractor {

    fun getBanners() : Observable<BannerResponse>
    fun getCategories() : Observable<CategoriesResponse>
    fun getProductsMoreSallers() : Observable<ProductResponse>
}