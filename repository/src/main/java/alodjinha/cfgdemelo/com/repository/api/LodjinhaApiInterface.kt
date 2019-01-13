package alodjinha.cfgdemelo.com.repository.api

import alodjinha.cfgdemelo.com.model.BannersResponse
import alodjinha.cfgdemelo.com.model.CategoriesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers

interface LodjinhaApiInterface {

    @Headers("Accept: application/json")
    @GET("banner")
    fun getBanners(): Single<BannersResponse>

    @Headers("Accept: application/json")
    @GET("categoria")
    fun getCategories(): Single<CategoriesResponse>
}