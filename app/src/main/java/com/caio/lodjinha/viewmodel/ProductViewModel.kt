package com.caio.lodjinha.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.caio.lodjinha.di.ApplicationBase
import com.caio.lodjinha.repository.ProductRepository
import com.caio.lodjinha.repository.entity.Product
import com.caio.lodjinha.repository.remote.RemoteResponse
import com.caio.lodjinha.repository.remote.io.ProductReservationResponse
import com.caio.lodjinha.repository.remote.io.ProductResponse
import javax.inject.Inject

class ProductViewModel : ViewModel() {

    @Inject
    lateinit var productRepository: ProductRepository

    private var ldProductMoreSallers: LiveData<RemoteResponse<ProductResponse>>? = null
    private var ldProductByCategoria: LiveData<RemoteResponse<ProductResponse>>? = null
    private var ldProductDetail: LiveData<RemoteResponse<Product>>? = null
    private var ldProductReservation: LiveData<RemoteResponse<ProductReservationResponse>>? = null

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

    fun getProductDetail(produtoId: Int): LiveData<RemoteResponse<Product>>{
        ldProductDetail = MutableLiveData<RemoteResponse<Product>>()

        productRepository.getProductDetail(produtoId)
            .subscribe({ result ->
                (ldProductDetail as MutableLiveData<RemoteResponse<Product?>>).value = result},
                { t: Throwable? -> t?.printStackTrace() })

        return ldProductDetail as MutableLiveData<RemoteResponse<Product>>
    }

    fun productReservation(produtoId: Int): LiveData<RemoteResponse<ProductReservationResponse>>{
        ldProductReservation = MutableLiveData<RemoteResponse<ProductReservationResponse>>()

        productRepository.productReservation(produtoId)
            .subscribe({ result ->
                (ldProductReservation as MutableLiveData<RemoteResponse<ProductReservationResponse?>>).value = result},
                { t: Throwable? -> t?.printStackTrace() })

        return ldProductReservation as MutableLiveData<RemoteResponse<ProductReservationResponse>>
    }
}