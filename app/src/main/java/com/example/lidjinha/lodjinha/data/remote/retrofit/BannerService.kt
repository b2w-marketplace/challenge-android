package com.example.lidjinha.lodjinha.data.remote.retrofit

import com.example.lidjinha.lodjinha.BuildConfig
import com.example.lidjinha.lodjinha.data.model.WSResponse
import com.example.lidjinha.lodjinha.model.Banner
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BannerService {

    @GET(BuildConfig.banner_url)
    fun banners(): Call<WSResponse<List<Banner>>>
}