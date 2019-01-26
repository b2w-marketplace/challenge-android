package com.sumiya.olodjinha.Service.Interface

import com.sumiya.olodjinha.Constants.APIConstants
import com.sumiya.olodjinha.Model.ProductDataModel
import com.sumiya.olodjinha.Model.ProductModel
import com.sumiya.olodjinha.Model.ReservationModel

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface APIProduct {
    @GET(APIConstants.PRODUCT_ENDPOINT)
    fun list(@Query(APIConstants.OFFSET_QUERY) offset: Int,
             @Query(APIConstants.LIMIT_QUERY) limit: Int,
             @Query(APIConstants.CATEGORY_ID_QUERY) categoriaId: Int): Call<ProductDataModel>

    @GET(APIConstants.PRODUCT_ENDPOINT + APIConstants.BEST_SELLER_ENDPOINT)
    fun listTop(): Call<ProductDataModel>

    @GET(APIConstants.PRODUCT_ENDPOINT + "/{" + APIConstants.PRODUCT_ID_PATH + "}")
    fun get(@Path(APIConstants.PRODUCT_ID_PATH) produtoId: Int): Call<ProductModel>

    @POST(APIConstants.PRODUCT_ENDPOINT + "/{" + APIConstants.PRODUCT_ID_PATH + "}")
    fun post(@Path(APIConstants.PRODUCT_ID_PATH) produtoId: Int): Call<ReservationModel>
}