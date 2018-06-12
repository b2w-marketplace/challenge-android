package com.example.lidjinha.lodjinha.data.remote.retrofit

import com.example.lidjinha.lodjinha.BuildConfig
import com.example.lidjinha.lodjinha.data.model.WSResponse
import com.example.lidjinha.lodjinha.model.Product
import retrofit2.Call
import retrofit2.http.GET

interface ProductService {

    @GET(BuildConfig.best_sellers_url)
    fun bestSellers(): Call<WSResponse<List<Product>>>
}