package com.sumiya.olodjinha.service.`interface`

import com.sumiya.olodjinha.constants.APIConstants
import com.sumiya.olodjinha.model.CategoryDataModel
import retrofit2.Call
import retrofit2.http.GET

interface APICategory {
    @GET(APIConstants.CATEGORY_ENDPOINT)
    fun list(): Call<CategoryDataModel>
}