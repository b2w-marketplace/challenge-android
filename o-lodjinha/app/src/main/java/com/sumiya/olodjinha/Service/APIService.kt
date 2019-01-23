package com.sumiya.olodjinha.Service

import com.sumiya.olodjinha.Constants.APIConstants

import com.sumiya.olodjinha.Service.Interface.APIBanner
import com.sumiya.olodjinha.Service.Interface.APICategory
import com.sumiya.olodjinha.Service.Interface.APIProduct
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIService {
    private val retrofit = Retrofit.Builder()
            .baseUrl(APIConstants.apiUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun banners() = retrofit.create(APIBanner::class.java)

    fun categories() = retrofit.create(APICategory::class.java)

    fun product() = retrofit.create(APIProduct::class.java)
}