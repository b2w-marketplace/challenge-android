package br.com.b2w.desafio.data.remote

import br.com.b2w.desafio.model.Categoria
import br.com.b2w.desafio.model.response.LodjinhaResponse
import retrofit2.Call
import retrofit2.http.*

interface CategoriaService {
    @GET("categoria")
    fun listar(
    ): Call<LodjinhaResponse<MutableList<Categoria>>>
}