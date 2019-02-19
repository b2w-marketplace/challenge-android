package br.com.b2w.lodjinha.views

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.b2w.lodjinha.Api
import br.com.b2w.lodjinha.features.product.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductDetailsViewModel(private val api: Api) : ViewModel() {

    val productLiveData: MutableLiveData<Product> = MutableLiveData()
    val saveProductLiveData: MutableLiveData<SaveProductState> = MutableLiveData()

    suspend fun getProduct(id: Int) = withContext(Dispatchers.IO) {
        productLiveData.postValue(api.getProduct(id).await())
    }

    suspend fun saveProduct(id: Int) = withContext(Dispatchers.IO) {
        val response = api.saveProduct(id).await()
        val state = if (response.isSuccessful && response.body()?.message?.isEmpty() != false) {
            SaveProductState.SuccessState
        } else {
            SaveProductState.ErrorState(
                response.body()?.message ?: ""
            )
        }
        saveProductLiveData.postValue(state)
    }

    sealed class SaveProductState {
        object SuccessState : SaveProductState()
        data class ErrorState(val message: String) : SaveProductState()
    }
}