package com.sumiya.olodjinha.service.`interface`

import com.sumiya.olodjinha.constants.APIConstants
import com.sumiya.olodjinha.model.BannerDataModel
import retrofit2.Call
import retrofit2.http.GET

interface APIBanner {
    @GET(APIConstants.BANNER_ENDPOINT)
    fun list(): Call<BannerDataModel>
}