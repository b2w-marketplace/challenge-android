package br.com.b2w.desafio.data.remote

import br.com.b2w.desafio.model.Banner
import br.com.b2w.desafio.model.response.LodjinhaResponse
import retrofit2.Call
import retrofit2.http.*

interface BannerService {
    @GET("banner")
    fun listar(
    ): Call<LodjinhaResponse<MutableList<Banner>>>
}