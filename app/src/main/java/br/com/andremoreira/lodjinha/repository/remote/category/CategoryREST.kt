package br.com.andremoreira.lodjinha.repository.remote.category

import br.com.andremoreira.lodjinha.repository.RemoteConstant
import br.com.andremoreira.lodjinha.repository.remote.io.CategoriesResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface CategoryREST {

    @Headers(RemoteConstant.ACCEPT_JSON)
    @GET(RemoteConstant.CATEGORY)
    fun getCategory(): Observable<Response<CategoriesResponse>>
}