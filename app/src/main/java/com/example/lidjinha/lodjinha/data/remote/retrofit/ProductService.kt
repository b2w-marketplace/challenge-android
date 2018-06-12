package com.example.lidjinha.lodjinha.data.remote.retrofit

import com.example.lidjinha.lodjinha.BuildConfig
import com.example.lidjinha.lodjinha.data.model.WSReserveResponse
import com.example.lidjinha.lodjinha.data.model.WSResponse
import com.example.lidjinha.lodjinha.model.Product
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ProductService {

    @GET(BuildConfig.best_sellers_url)
    fun bestSellers(): Call<WSResponse<List<Product>>>

    @POST(BuildConfig.reserve_url + "{productId}")
    fun reserve(@Body emptyJson: JSONObject, @Path("productId") productId: kotlin.Int): Call<WSReserveResponse>

}