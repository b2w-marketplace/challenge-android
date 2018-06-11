package com.example.lidjinha.lodjinha.data.remote.retrofit

import com.example.lidjinha.lodjinha.BuildConfig
import com.example.lidjinha.lodjinha.data.model.WSResponse
import com.example.lidjinha.lodjinha.model.Categorie
import retrofit2.Call
import retrofit2.http.GET

interface CategoriesService {

    @GET(BuildConfig.categories_url)
    fun categories(): Call<WSResponse<List<Categorie>>>
}