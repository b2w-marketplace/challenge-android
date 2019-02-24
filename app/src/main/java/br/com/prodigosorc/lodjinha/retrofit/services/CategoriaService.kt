package br.com.prodigosorc.lodjinha.retrofit.services

import br.com.prodigosorc.lodjinha.retrofit.services.dto.CategoriaSync
import retrofit2.Call
import retrofit2.http.GET

interface CategoriaService {

    @GET("categoria")
    fun listaCategoria(): Call<CategoriaSync>

}