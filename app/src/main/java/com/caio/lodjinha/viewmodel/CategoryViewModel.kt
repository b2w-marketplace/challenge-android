package com.caio.lodjinha.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.caio.lodjinha.di.ApplicationBase
import com.caio.lodjinha.repository.BannerRepository
import com.caio.lodjinha.repository.remote.CategoryRepository
import com.caio.lodjinha.repository.remote.RemoteResponse
import com.caio.lodjinha.repository.remote.io.BannerResponse
import javax.inject.Inject

class CategoryViewModel : ViewModel() {

    @Inject
    lateinit var categoryRepository: CategoryRepository

    private var liveDataCategory: LiveData<RemoteResponse<BannerResponse>>? = null

    init {
        initializeDagger()
    }
    private fun initializeDagger() = ApplicationBase.appComponent.inject(this)


    fun getBanner(): LiveData<RemoteResponse<BannerResponse>>{
        liveDataCategory = MutableLiveData<RemoteResponse<BannerResponse>>()

        categoryRepository.getCategory()
            .subscribe({ result ->
                (liveDataCategory as MutableLiveData<RemoteResponse<BannerResponse?>>).value = result},
                { t: Throwable? -> t?.printStackTrace() })

        return liveDataCategory as MutableLiveData<RemoteResponse<BannerResponse>>
    }
}