package com.eric.alodjinha.features.home.api

import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers

interface ProductApi {

    @Headers("Accept: application/json")
    @GET("/banner")
    fun getBanner(): Observable<BannerResponse>
}