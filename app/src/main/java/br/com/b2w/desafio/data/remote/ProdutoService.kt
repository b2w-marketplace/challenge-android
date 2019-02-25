package br.com.b2w.desafio.data.remote

import br.com.b2w.desafio.model.produto.Produto
import br.com.b2w.desafio.model.response.LodjinhaResponse
import retrofit2.Call
import retrofit2.http.*

interface ProdutoService {
    @GET("produto")
    fun listar(
        @Query("offset") key: Int?,
        @Query("limit") title: Int?,
        @Query("categoriaId") type: Int?
    ): Call<LodjinhaResponse<MutableList<Produto>>>

    @GET("produto/maisvendidos")
    fun listarMaisVendidos(
    ): Call<LodjinhaResponse<MutableList<Produto>>>

    @GET("produto/{produtoId}")
    fun getById(
        @Path("produtoId") produtoId: Int
    ): Call<LodjinhaResponse<Produto>>

    @POST("produto/{produtoId}")
    fun postById(
        @Path("produtoId") produtoId: Int
    ): Call<LodjinhaResponse<Produto>>
}