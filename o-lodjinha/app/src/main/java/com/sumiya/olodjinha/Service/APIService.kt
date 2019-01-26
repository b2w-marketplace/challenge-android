package com.sumiya.olodjinha.service

import com.sumiya.olodjinha.constants.APIConstants
import com.sumiya.olodjinha.service.`interface`.APIBanner
import com.sumiya.olodjinha.service.`interface`.APICategory
import com.sumiya.olodjinha.service.`interface`.APIProduct
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIService {
    private val retrofit = Retrofit.Builder()
            .baseUrl(APIConstants.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

//    Banners
    fun banners() = retrofit.create(APIBanner::class.java)

//    Categorias
    fun categories() = retrofit.create(APICategory::class.java)

//    Produtos
    fun product() = retrofit.create(APIProduct::class.java)
}