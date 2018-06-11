package com.example.lidjinha.lodjinha.data.remote.repository

import android.util.Log
import com.example.lidjinha.lodjinha.data.model.WSResponse
import com.example.lidjinha.lodjinha.data.remote.retrofit.RetrofitService
import com.example.lidjinha.lodjinha.model.Banner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.reflect.KFunction1

class BannerRepository {

    private lateinit var callGames: Call<WSResponse<List<Banner>>>

    companion object {
        val instance: BannerRepository by lazy { BannerRepository() }
    }

    fun getBanners(onBannersRetrieved: KFunction1<List<Banner>, Unit>) {
        callGames = RetrofitService.getBannerService().banners()
        callGames.enqueue(bannersCallback(onBannersRetrieved))
    }

    private fun bannersCallback(onBannersRetrieved: KFunction1<List<Banner>, Unit>) = object : Callback<WSResponse<List<Banner>>> {

        override fun onResponse(call: Call<WSResponse<List<Banner>>>, response: Response<WSResponse<List<Banner>>>) {
            response.apply { this.body()?.let { it.body?.let { it1 -> onBannersRetrieved(it1) } } }
        }

        override fun onFailure(call: Call<WSResponse<List<Banner>>>, t: Throwable) {
            Log.d("Banners Failure:", t.message)
        }
    }
}