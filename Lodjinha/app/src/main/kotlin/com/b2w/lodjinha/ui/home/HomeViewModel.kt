package com.b2w.lodjinha.ui.home

import android.net.NetworkInfo
import com.b2w.lodjinha.data.ResourceLiveData
import com.b2w.lodjinha.data.SingleLiveData
import com.b2w.lodjinha.data.api.LodjinhaClient
import com.b2w.lodjinha.data.model.BannerResponse
import com.b2w.lodjinha.data.model.BestSellingResponse
import com.b2w.lodjinha.data.model.CategoryResponse
import com.b2w.lodjinha.ui.BaseViewModel
import com.b2w.lodjinha.util.doOnSubscribeConnectedToNetwork
import com.b2w.lodjinha.util.toErrorHandler

class HomeViewModel: BaseViewModel() {

    val bannersResult = ResourceLiveData<BannerResponse>()
    val categoriesResult = ResourceLiveData<CategoryResponse>()
    val bestSellingResult = ResourceLiveData<BestSellingResponse>()

    val loadingBanner = SingleLiveData<Boolean>()
    val loadingCategories = SingleLiveData<Boolean>()
    val loadingBestSelling = SingleLiveData<Boolean>()

    fun fetchBanners(networkInfo: NetworkInfo?) {
        LodjinhaClient().getInstance().fetchBanners()
            .doOnSubscribeConnectedToNetwork(networkInfo) {
                loadingBanner.postValue(true)
            }
            .doFinally { loadingBanner.postValue(false) }
            .toErrorHandler()
            .subscribeLiveData(this, bannersResult, true)
    }

    fun fetchCategories(networkInfo: NetworkInfo?) {
        LodjinhaClient().getInstance().fetchCategories()
            .doOnSubscribeConnectedToNetwork(networkInfo) {
                loadingCategories.postValue(true)
            }
            .doFinally { loadingCategories.postValue(false) }
            .toErrorHandler()
            .subscribeLiveData(this, categoriesResult, true)
    }

    fun fetchBestSelling(networkInfo: NetworkInfo?) {
        LodjinhaClient().getInstance().fetchBestSelling()
            .doOnSubscribeConnectedToNetwork(networkInfo) {
                loadingBestSelling.postValue(true)
            }
            .doFinally { loadingBestSelling.postValue(false) }
            .toErrorHandler()
            .subscribeLiveData(this, bestSellingResult, true)
    }
}