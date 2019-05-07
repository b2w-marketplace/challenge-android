package br.com.amedigital.network

import br.com.amedigital.network.model.BodyRequestBanner
import br.com.amedigital.network.model.BodyRequestCategory
import br.com.amedigital.network.model.BodyRequestProduct
import retrofit2.Call
import retrofit2.http.*

interface LojinhaService  {

    @GET("banner")
    fun getBanner(): Call<BodyRequestBanner>

    @GET("categoria")
    fun getCategory(): Call<BodyRequestCategory>

    @GET("produto")
    fun getProductByCategory(@Query("categoriaId") id: Int): Call<BodyRequestProduct>

    @GET("produto/maisvendidos")
    fun getTopSellingProducts(): Call<BodyRequestProduct>

    @POST("produto/{produtoId}")
    fun setReservation(@Path("produtoId") id: Int) : Call<Void>
}