package alodjinha.cfgdemelo.com.repository.home

import alodjinha.cfgdemelo.com.model.BannersResponse
import alodjinha.cfgdemelo.com.model.BestSellersResponse
import alodjinha.cfgdemelo.com.model.CategoriesResponse
import alodjinha.cfgdemelo.com.repository.api.LodjinhaApi
import io.reactivex.Single

class HomeRepository {

    val lodjinhaApi by lazy { LodjinhaApi() }

    fun getBanners(): Single<BannersResponse> = lodjinhaApi.getBanners()
    fun getCategories(): Single<CategoriesResponse> = lodjinhaApi.getCategories()
    fun getBestSellers(): Single<BestSellersResponse> = lodjinhaApi.getBestSellers()
}