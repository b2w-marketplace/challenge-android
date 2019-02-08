package com.caio.lodjinha.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.caio.lodjinha.di.ApplicationBase
import com.caio.lodjinha.repository.BannerRepository
import com.caio.lodjinha.repository.remote.RemoteResponse
import com.caio.lodjinha.repository.remote.io.BannerResponse
import javax.inject.Inject

class BannerViewModel : ViewModel() {

    @Inject
    lateinit var bannerRepository: BannerRepository

    private var liveDataBanner: LiveData<RemoteResponse<BannerResponse>>? = null

    init {
        initializeDagger()
    }
    private fun initializeDagger() = ApplicationBase.appComponent.inject(this)


    fun getBanner(): LiveData<RemoteResponse<BannerResponse>>{
        liveDataBanner = MutableLiveData<RemoteResponse<BannerResponse>>()

        bannerRepository.getBanner()
            .subscribe({ result ->
                (liveDataBanner as MutableLiveData<RemoteResponse<BannerResponse?>>).value = result},
                { t: Throwable? -> t?.printStackTrace() })

        return liveDataBanner as MutableLiveData<RemoteResponse<BannerResponse>>
    }
}