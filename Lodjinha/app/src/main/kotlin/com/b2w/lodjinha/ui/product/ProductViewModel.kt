package com.b2w.lodjinha.ui.product

import android.net.NetworkInfo
import com.b2w.lodjinha.data.ResourceLiveData
import com.b2w.lodjinha.data.SingleLiveData
import com.b2w.lodjinha.data.api.LodjinhaClient
import com.b2w.lodjinha.data.model.NoContentResponse
import com.b2w.lodjinha.data.model.ProductsResponse
import com.b2w.lodjinha.ui.BaseViewModel
import com.b2w.lodjinha.util.doOnSubscribeConnectedToNetwork
import com.b2w.lodjinha.util.toErrorHandler

class ProductViewModel: BaseViewModel() {

    val reservationResult = ResourceLiveData<NoContentResponse>()

    val loadingReservation = SingleLiveData<Boolean>()

    fun doReservation(networkInfo: NetworkInfo?, productId: Int) {
        LodjinhaClient().getInstance().doReservation(productId)
            .doOnSubscribeConnectedToNetwork(networkInfo) {
                loadingReservation.postValue(true)
            }
            .doFinally { loadingReservation.postValue(false) }
            .toErrorHandler()
            .subscribeLiveData(this, reservationResult, true)
    }
}