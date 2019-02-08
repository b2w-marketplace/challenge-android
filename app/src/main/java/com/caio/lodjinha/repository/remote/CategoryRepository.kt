package com.caio.lodjinha.repository.remote

import com.caio.lodjinha.repository.remote.RemoteResponse
import com.caio.lodjinha.repository.remote.banner.BannerRemoteRep
import com.caio.lodjinha.repository.remote.category.CategoryRemoteRep
import com.caio.lodjinha.repository.remote.io.BannerResponse
import com.caio.lodjinha.repository.remote.validateHttpCode
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryRepository @Inject constructor(
    private val categoryRemoteRep: CategoryRemoteRep
) {

    private val TAG: String = this::class.java.simpleName

    fun getCategory() : Observable<RemoteResponse<BannerResponse?>> {

        return categoryRemoteRep.getCategory()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .validateHttpCode()
//            .doOnSubscribe {if(isLoading!!) loadingDialog.showLoading() }
//            .doOnTerminate{if(isLoading!!) loadingDialog.dismissLoading()}

    }


}