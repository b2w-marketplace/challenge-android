package marcus.com.br.b2wtest.model.service

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import marcus.com.br.b2wtest.model.api.Api
import marcus.com.br.b2wtest.model.data.BannerResult
import javax.inject.Inject

class BannerService @Inject constructor(private val api: Api) {

    fun getBanners(): Flowable<BannerResult> {
        return api.getBanners()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}