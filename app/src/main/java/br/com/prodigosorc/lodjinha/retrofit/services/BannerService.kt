package br.com.prodigosorc.lodjinha.retrofit.services

import br.com.prodigosorc.lodjinha.retrofit.services.dto.BannerSync
import retrofit2.Call
import retrofit2.http.GET

interface BannerService {

    @GET("banner")
    fun carregaBanner(): Call<BannerSync>

}