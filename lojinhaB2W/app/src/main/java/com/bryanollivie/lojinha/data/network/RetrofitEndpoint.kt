package com.bryanollivie.lojinha.data.network

import com.bryanollivie.lojinha.data.model.ReturnBase
import retrofit2.Call
import retrofit2.http.*

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
    fun produtoFindByCategoria(@Query("categoriaId") categoriaId: Int, @Query("offset") offset: Int, @Query("limit") limit: Int): Call<ReturnBase>

    @GET("./produto/maisvendidos")
    fun produtoMaisVendidos(): Call<ReturnBase>

    @GET("./produto")
    fun produtoFindById(@Path("produtoId") produtoId: Int): Call<ReturnBase>

    @POST("./produto")
    fun reservarProdutoById(@Path("produtoId") produtoId: Int): Call<ReturnBase>
    //endregion
}
