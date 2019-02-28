package com.caio.lodjinha.repository.remote

import com.caio.lodjinha.repository.RemoteConstant
import com.caio.lodjinha.repository.remote.io.CategoriesResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Headers

interface CategoryREST {

    @Headers(RemoteConstant.ACCEPT_JSON)
    @GET(RemoteConstant.CATEGORY)
    fun getCategory(): Deferred<CategoriesResponse>
}