package com.sumiya.olodjinha.Service.Interface

import com.sumiya.olodjinha.Constants.APIConstants
import com.sumiya.olodjinha.Model.CategoryDataModel
import retrofit2.Call
import retrofit2.http.GET

interface APICategory {
    @GET(APIConstants.CATEGORY_ENDPOINT)
    fun list(): Call<CategoryDataModel>
}