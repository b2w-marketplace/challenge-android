package br.com.amedigital.challenge_android.api

import androidx.lifecycle.LiveData
import br.com.amedigital.challenge_android.models.network.GetBannersResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface BannerService {

    /**
     * Get banner list
     */
    @GET("banner")
    fun getBannerList(): LiveData<ApiResponse<GetBannersResponse>>
}