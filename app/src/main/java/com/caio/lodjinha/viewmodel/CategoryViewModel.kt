package com.caio.lodjinha.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.caio.lodjinha.di.ApplicationBase
import com.caio.lodjinha.repository.BannerRepository
import com.caio.lodjinha.repository.remote.CategoryRepository
import com.caio.lodjinha.repository.remote.RemoteResponse
import com.caio.lodjinha.repository.remote.io.BannerResponse
import com.caio.lodjinha.repository.remote.io.CategoriesResponse
import javax.inject.Inject

class CategoryViewModel : ViewModel() {

    @Inject
    lateinit var categoryRepository: CategoryRepository

    private var liveDataCategory: LiveData<RemoteResponse<CategoriesResponse>>? = null

    init {
        initializeDagger()
    }
    private fun initializeDagger() = ApplicationBase.appComponent.inject(this)


    fun getCategory(): LiveData<RemoteResponse<CategoriesResponse>>{
        liveDataCategory = MutableLiveData<RemoteResponse<CategoriesResponse>>()

        categoryRepository.getCategory()
            .subscribe({ result ->
                (liveDataCategory as MutableLiveData<RemoteResponse<CategoriesResponse?>>).value = result},
                { t: Throwable? -> t?.printStackTrace() })

        return liveDataCategory as MutableLiveData<RemoteResponse<CategoriesResponse>>
    }
}