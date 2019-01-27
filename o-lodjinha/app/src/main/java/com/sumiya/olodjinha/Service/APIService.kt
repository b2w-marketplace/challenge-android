package com.sumiya.olodjinha.service

import com.sumiya.olodjinha.constants.APIConstants
import com.sumiya.olodjinha.service.`interface`.APIBanner
import com.sumiya.olodjinha.service.`interface`.APICategory
import com.sumiya.olodjinha.service.`interface`.APIProduct
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class APIService {
    //Variables
    val okHttp = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .build()

    private val retrofit = Retrofit.Builder()
            .baseUrl(APIConstants.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp)
            .build()

    //API Methods

    //    Banners
    fun banners() = retrofit.create(APIBanner::class.java)

    //    Categorias
    fun categories() = retrofit.create(APICategory::class.java)

    //    Produtos
    fun product() = retrofit.create(APIProduct::class.java)
}