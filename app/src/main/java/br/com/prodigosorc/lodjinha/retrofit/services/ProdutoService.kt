package br.com.prodigosorc.lodjinha.retrofit.services

import br.com.prodigosorc.lodjinha.retrofit.services.dto.ProdutoSync
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ProdutoService {

    @GET("produto")
    fun listaProdutos(): Call<ProdutoSync>

    @GET("produto/maisvendidos")
    fun listaMaiVendidos(): Call<ProdutoSync>

    @POST("produto/{produtoId}")
    fun reservarProduto(@Path("produtoId") produtoId: Int): Call<Void>
}