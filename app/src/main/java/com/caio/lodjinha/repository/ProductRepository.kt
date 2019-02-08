package com.caio.lodjinha.repository

import com.caio.lodjinha.repository.remote.RemoteResponse
import com.caio.lodjinha.repository.remote.io.ProductResponse
import com.caio.lodjinha.repository.remote.product.ProductRemoteRep
import com.caio.lodjinha.repository.remote.validateHttpCode
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor(
    private val productRemoteRep: ProductRemoteRep
) {

    private val TAG: String = this::class.java.simpleName

    fun getProductsMoreSallers() : Observable<RemoteResponse<ProductResponse?>> {

        return productRemoteRep.getProductsMoreSallers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .validateHttpCode()
//            .doOnSubscribe {if(isLoading!!) loadingDialog.showLoading() }
//            .doOnTerminate{if(isLoading!!) loadingDialog.dismissLoading()}

    }


}