package com.eric.alodjinha.features.home.api

import io.reactivex.Observable

class HomeFragmentInteractorImpl : HomeFragmentInteractor{

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
}