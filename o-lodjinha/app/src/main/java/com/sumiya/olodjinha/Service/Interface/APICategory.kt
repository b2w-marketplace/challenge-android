package com.sumiya.olodjinha.Service.Interface

import com.sumiya.olodjinha.Model.CategoryDataModel
import retrofit2.Call
import retrofit2.http.GET

interface APICategory {
    @GET("categoria")
    fun list(): Call<CategoryDataModel>
}