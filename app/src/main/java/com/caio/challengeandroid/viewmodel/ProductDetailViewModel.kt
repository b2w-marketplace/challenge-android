package com.caio.lodjinha.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.caio.lodjinha.repository.ProductRepository
import com.caio.lodjinha.repository.entity.Product

class ProductDetailViewModel(
    private val productRepository: ProductRepository) : ViewModel() {

    private val TAG : String = "ProductDetailViewModel"

    val productLiveData: MutableLiveData<Product> = MutableLiveData()

    suspend fun getProductDetail(produtoId: Int){
        productLiveData.postValue(productRepository.getProductDetail(produtoId))
    }

}