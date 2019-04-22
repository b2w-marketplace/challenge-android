package com.desafioamedigital.service.api

import com.desafioamedigital.model.dto.BannerList
import com.desafioamedigital.util.Constants
import io.reactivex.Observable
import retrofit2.http.GET

interface BannerService {

    @GET(Constants.BANNER_ENDPOINT)
    fun getBanners(): Observable<BannerList>

}