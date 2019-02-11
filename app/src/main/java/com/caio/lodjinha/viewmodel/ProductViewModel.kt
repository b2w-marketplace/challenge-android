package com.caio.lodjinha.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.caio.lodjinha.di.ApplicationBase
import com.caio.lodjinha.repository.ProductRepository
import com.caio.lodjinha.repository.remote.RemoteResponse
import com.caio.lodjinha.repository.remote.io.ProductResponse
import javax.inject.Inject

class ProductViewModel : ViewModel() {

    @Inject
    lateinit var productRepository: ProductRepository

    private var ldProductMoreSallers: LiveData<RemoteResponse<ProductResponse>>? = null
    private var ldProductByCategoria: LiveData<RemoteResponse<ProductResponse>>? = null

    init {
        initializeDagger()
    }
    private fun initializeDagger() = ApplicationBase.appComponent.inject(this)


    fun getProductsMoreSallers(): LiveData<RemoteResponse<ProductResponse>>{
        ldProductMoreSallers = MutableLiveData<RemoteResponse<ProductResponse>>()

        productRepository.getProductsMoreSallers()
            .subscribe({ result ->
                (ldProductMoreSallers as MutableLiveData<RemoteResponse<ProductResponse?>>).value = result},
                { t: Throwable? -> t?.printStackTrace() })

        return ldProductMoreSallers as MutableLiveData<RemoteResponse<ProductResponse>>
    }

    fun getProductsByCategory(offset: Int, limite: Int,categoriaId: Int): LiveData<RemoteResponse<ProductResponse>>{
        ldProductByCategoria = MutableLiveData<RemoteResponse<ProductResponse>>()

        productRepository.getProductsByCategory(offset, limite, categoriaId)
            .subscribe({ result ->
                (ldProductByCategoria as MutableLiveData<RemoteResponse<ProductResponse?>>).value = result},
                { t: Throwable? -> t?.printStackTrace() })

        return ldProductByCategoria as MutableLiveData<RemoteResponse<ProductResponse>>
    }
}