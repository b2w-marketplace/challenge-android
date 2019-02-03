package com.eric.alodjinha.features.home.api

import com.eric.alodjinha.features.product.api.ProductResponse
import io.reactivex.Observable

interface HomeFragmentInteractor {

    fun getBanners() : Observable<BannerResponse>
    fun getCategories() : Observable<CategoriesResponse>
    fun getProductsMoreSallers() : Observable<ProductResponse>
}