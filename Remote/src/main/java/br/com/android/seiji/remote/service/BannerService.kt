package br.com.android.seiji.remote.service

import br.com.android.seiji.remote.model.BannerResponseModel
import io.reactivex.Flowable
import retrofit2.http.GET

interface BannerService {

    @GET("/banner")
    fun getBanners(): Flowable<BannerResponseModel>
}