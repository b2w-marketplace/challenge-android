package com.caio.lodjinha.repository

import com.caio.lodjinha.repository.remote.RemoteResponse
import com.caio.lodjinha.repository.remote.banner.BannerRemoteRep
import com.caio.lodjinha.repository.remote.io.BannerResponse
import com.caio.lodjinha.repository.remote.validateHttpCode
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BannerRepository @Inject constructor(
    private val bannerRemoteRep: BannerRemoteRep
) {

    private val TAG: String = this::class.java.simpleName

    fun getBanner() : Observable<RemoteResponse<BannerResponse?>> {

        return bannerRemoteRep.getBanner()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .validateHttpCode()
//            .doOnSubscribe {if(isLoading!!) loadingDialog.showLoading() }
//            .doOnTerminate{if(isLoading!!) loadingDialog.dismissLoading()}

    }


}