package br.com.dafle.alodjinha.service

import br.com.dafle.alodjinha.model.BannerResponse
import br.com.dafle.alodjinha.model.CategoryResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface HomeService {

    @GET("/banner")
    fun fetchBanner(): Observable<BannerResponse>

    @GET("/categoria")
    fun fetchCategory(): Observable<CategoryResponse>
}