package com.caio.lodjinha.repository.remote.banner

import com.caio.lodjinha.repository.RemoteConstant
import com.caio.lodjinha.repository.remote.io.BannerResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface BannerREST {

    @Headers(RemoteConstant.ACCEPT_JSON)
    @GET(RemoteConstant.BANNER)
    fun getBanner(): Observable<Response<BannerResponse>>
}