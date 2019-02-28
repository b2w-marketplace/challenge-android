package com.caio.lodjinha.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.caio.lodjinha.repository.ProductRepository
import com.caio.lodjinha.repository.entity.Product

class ProductDetailViewModel(
    private val productRepository: ProductRepository) : ViewModel() {

    val productLiveData: MutableLiveData<Product> = MutableLiveData()

    val reservationProductLiveData: MutableLiveData<ReservationProductState> = MutableLiveData()

    suspend fun getProductDetail(produtoId: Int){
        productLiveData.postValue(productRepository.getProductDetail(produtoId))
    }

    suspend fun productReservation(productId: Int){
        val response = productRepository.productReservation(productId)
        val state = if (response.isSuccessful && response.body()?.result?.isEmpty() == false) {
            ReservationProductState.SuccessState
        } else {
            ReservationProductState.ErrorState(
                response.body()?.result ?: "Não foi possível reservar o produto"
            )
        }
        reservationProductLiveData.postValue(state)
    }

    sealed class ReservationProductState {
        object SuccessState : ReservationProductState()
        data class ErrorState(val message: String) : ReservationProductState()
    }

}