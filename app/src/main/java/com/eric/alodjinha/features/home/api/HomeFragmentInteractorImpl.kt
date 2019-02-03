package com.eric.alodjinha.features.home.api

import com.eric.alodjinha.features.product.api.ProductResponse
import io.reactivex.Observable

class HomeFragmentInteractorImpl : HomeFragmentInteractor {

    val repository = HomeFragmentRepository()

    companion object {

        val instance = HomeFragmentInteractorImpl()
    }

    override fun getBanners(): Observable<BannerResponse> {

        return repository.getBanner().map { it }
    }

    override fun getCategories(): Observable<CategoriesResponse> {
        return repository.getCategories().map { it }
    }

    override fun getProductsMoreSallers(): Observable<ProductResponse> {

        return repository.getProductsMoreSallers().map { it }
    }
}