package br.com.andremoreira.lodjinha.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import br.com.andremoreira.lodjinha.di.ApplicationBase
import br.com.andremoreira.lodjinha.repository.remote.CategoryRepository
import br.com.andremoreira.lodjinha.repository.remote.RemoteResponse
import br.com.andremoreira.lodjinha.repository.remote.io.CategoriesResponse
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