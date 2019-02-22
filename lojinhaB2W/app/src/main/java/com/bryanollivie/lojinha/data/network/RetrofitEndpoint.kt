package com.bryanollivie.lojinha.data.network

import com.bryanollivie.lojinha.data.model.Produto
import com.bryanollivie.lojinha.data.model.ReturnBase
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitEndpoint {

    //region Banner
    @GET("./banner")
    fun bannerFindAll(): Call<ReturnBase>
    //endregion

    //region Categoria
    @GET("./categoria")
    fun categoriaFindAll(): Call<ReturnBase>
    //endregion

    //region Produto
    @GET("./produto")
    fun produtoFindByCategoria(@Query("categoriaId") categoriaId: Int): Call<ReturnBase>

    @GET("./produto/maisvendidos")
    fun produtoMaisVendidos(): Call<ReturnBase>

    @GET("/produto/{produtoId}")
    fun produtoFindById(@Path("produtoId") produtoId: Int): Call<Produto>

    @POST("/produto/{produtoId}")
    fun reservarProdutoById(@Path("produtoId") produtoId: Int): Call<ReturnBase>
    //endregion
}
