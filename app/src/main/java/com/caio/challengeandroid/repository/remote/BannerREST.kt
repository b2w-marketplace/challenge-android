package com.caio.lodjinha.repository.remote

import com.caio.lodjinha.repository.RemoteConstant
import com.caio.lodjinha.repository.remote.io.BannerResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Headers

interface BannerREST {

    @Headers(RemoteConstant.ACCEPT_JSON)
    @GET(RemoteConstant.BANNER)
    fun getBanner(): Deferred<BannerResponse>
}