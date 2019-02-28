package com.caio.lodjinha.repository.remote

import com.caio.lodjinha.repository.RemoteConstant
import com.caio.lodjinha.repository.entity.Product
import com.caio.lodjinha.repository.remote.io.ProductReservationResponse
import com.caio.lodjinha.repository.remote.io.ProductResponse
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ProductREST {

    @Headers(RemoteConstant.ACCEPT_JSON)
    @GET(RemoteConstant.PRODUCT_MORE_SALLERS)
    fun getProductsMoreSallers(): Deferred<ProductResponse>

    @Headers(RemoteConstant.ACCEPT_JSON)
    @GET(RemoteConstant.PRODUCT_BY_CATEGORY)
    fun getProductsByCategory(
        @Query(RemoteConstant.QUERY_OFFSET) offset: Int,
        @Query(RemoteConstant.QUERY_LIMITE) limite: Int,
        @Query(RemoteConstant.QUERY_CATEGORY_ID) categoriaId: Int
    ): Call<ProductResponse>

    @Headers(RemoteConstant.ACCEPT_JSON)
    @POST(RemoteConstant.PRODUCT_BY_ID)
    fun productReservation(@Path(RemoteConstant.PARAM_PRODUCT_ID) productId: Int):Deferred<Response<ProductReservationResponse>>

    @Headers(RemoteConstant.ACCEPT_JSON)
    @GET(RemoteConstant.PRODUCT_BY_ID)
    fun getProductDetail(@Path(RemoteConstant.PARAM_PRODUCT_ID) productId: Int): Deferred<Product>
}