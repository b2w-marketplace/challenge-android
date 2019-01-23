package com.sumiya.olodjinha.Service.Interface

import com.sumiya.olodjinha.Model.BannerDataModel
import com.sumiya.olodjinha.Model.CategoryDataModel
import retrofit2.Call
import retrofit2.http.GET

interface APIBanner {
    @GET("banner")
    fun list(): Call<BannerDataModel>
}