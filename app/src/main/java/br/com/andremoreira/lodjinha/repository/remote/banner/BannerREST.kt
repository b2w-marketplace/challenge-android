package br.com.andremoreira.lodjinha.repository.remote.banner

import br.com.andremoreira.lodjinha.repository.RemoteConstant
import br.com.andremoreira.lodjinha.repository.remote.io.BannerResponse
import io.reactivex.Observable
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Headers


interface BannerREST {

    @Headers(RemoteConstant.ACCEPT_JSON)
    @GET(RemoteConstant.BANNER)
    fun getBanner(): Observable<Response<BannerResponse>>
}