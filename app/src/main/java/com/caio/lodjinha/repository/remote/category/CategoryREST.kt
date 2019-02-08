package com.caio.lodjinha.repository.remote.category

import com.caio.lodjinha.repository.RemoteConstant
import com.caio.lodjinha.repository.remote.io.BannerResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface CategoryREST {

    @Headers(RemoteConstant.ACCEPT_JSON)
    @GET(RemoteConstant.CATEGORY)
    fun getCategory(): Observable<Response<BannerResponse>>
}