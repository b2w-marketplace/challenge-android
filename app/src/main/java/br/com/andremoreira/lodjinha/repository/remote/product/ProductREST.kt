package br.com.andremoreira.lodjinha.repository.remote.product

import br.com.andremoreira.lodjinha.repository.RemoteConstant
import br.com.andremoreira.lodjinha.repository.entity.Product
import br.com.andremoreira.lodjinha.repository.remote.io.ProductReservationResponse
import br.com.andremoreira.lodjinha.repository.remote.io.ProductResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.*

interface ProductREST {

    @Headers(RemoteConstant.ACCEPT_JSON)
    @GET(RemoteConstant.PRODUCT_MORE_SALLERS)
    fun getProductsMoreSallers(): Observable<Response<ProductResponse>>

    @Headers(RemoteConstant.ACCEPT_JSON)
    @GET(RemoteConstant.PRODUCT_BY_CATEGORY)
    fun getProductsByCategory(
        @Query(RemoteConstant.QUERY_OFFSET) offset: Int,
        @Query(RemoteConstant.QUERY_LIMITE) limite: Int,
        @Query(RemoteConstant.QUERY_CATEGORY_ID) categoriaId: Int
    ): Observable<Response<ProductResponse>>

    @Headers(RemoteConstant.ACCEPT_JSON)
    @POST(RemoteConstant.PRODUCT_BY_ID)
    fun productReservation(@Path(RemoteConstant.PARAM_PRODUCT_ID) productId: Int): Observable<Response<ProductReservationResponse>>

    @Headers(RemoteConstant.ACCEPT_JSON)
    @GET(RemoteConstant.PRODUCT_BY_ID)
    fun getProductDetail(@Path(RemoteConstant.PARAM_PRODUCT_ID) productId: Int): Observable<Response<Product>>
}