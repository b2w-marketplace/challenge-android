package marcus.com.br.b2wtest.model.api

import io.reactivex.Flowable
import marcus.com.br.b2wtest.model.data.BannerResult
import retrofit2.http.GET

interface Api {

    @GET("banner")
    fun getBanners(): Flowable<BannerResult>
}