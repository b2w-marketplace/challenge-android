package br.com.b2w.lodjinha.features.product.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.b2w.lodjinha.Api
import br.com.b2w.lodjinha.features.product.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductDetailsViewModel(val api: Api) : ViewModel() {

    private val productLiveData: MutableLiveData<Product> = MutableLiveData()

    suspend fun getProduct(id: Int): LiveData<Product> = withContext(Dispatchers.IO) {
        productLiveData.apply { postValue(api.getProduct(id).await()) }
    }

}