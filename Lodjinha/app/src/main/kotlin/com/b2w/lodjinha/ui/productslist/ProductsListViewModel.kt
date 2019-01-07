package com.b2w.lodjinha.ui.productslist

import android.net.NetworkInfo
import com.b2w.lodjinha.data.ResourceLiveData
import com.b2w.lodjinha.data.SingleLiveData
import com.b2w.lodjinha.data.api.LodjinhaClient
import com.b2w.lodjinha.data.model.ProductsResponse
import com.b2w.lodjinha.ui.BaseViewModel
import com.b2w.lodjinha.util.doOnSubscribeConnectedToNetwork
import com.b2w.lodjinha.util.toErrorHandler

class ProductsListViewModel: BaseViewModel() {

    val productsResult = ResourceLiveData<ProductsResponse>()

    val loadingProducts = SingleLiveData<Boolean>()

    fun fetchProducts(networkInfo: NetworkInfo?, categoryId : Int, offset : Int, total: Int) {
        LodjinhaClient().getInstance().fetchProducts(categoryId, offset, total)
            .doOnSubscribeConnectedToNetwork(networkInfo) {
                loadingProducts.postValue(true)
            }
            .doFinally { loadingProducts.postValue(false) }
            .toErrorHandler()
            .subscribeLiveData(this, productsResult, true)
    }
}