package br.com.rbueno.lodjinha.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.rbueno.lodjinha.model.Product
import br.com.rbueno.lodjinha.repository.ProductRepository
import br.com.rbueno.lodjinha.util.handlerLoading
import javax.inject.Inject
import javax.inject.Singleton

class ProductViewModel(private val repository: ProductRepository) : BaseViewModel() {

    private val productMutableLiveData = MutableLiveData<Product>()
    private val reserveProductMutableLiveData = MutableLiveData<Unit>()

    val productLiveData: LiveData<Product>
        get() = productMutableLiveData

    val reserveProductLiveData: LiveData<Unit>
        get() = reserveProductMutableLiveData

    fun loadProduct(productId: Int) {
        disposables.add(
            repository.getProduct(productId)
                .handlerLoading(loadingMutableLiveData)
                .subscribe({
                    productMutableLiveData.postValue(it)
                }, {
                    handleError(it)
                })
        )
    }

    fun reserveProduct(productId: Int) {
        disposables.add(
            repository.reserveProduct(productId)
                .handlerLoading(loadingMutableLiveData)
                .subscribe({
                    reserveProductMutableLiveData.postValue(Unit)
                }, {
                    handleError(it)
                })
        )
    }

    fun clearProductLiveData() {
        productMutableLiveData.value = null
    }

    fun clearReserveProductLiveData() {
        reserveProductMutableLiveData.value = null
    }

    @Suppress("UNCHECKED_CAST")
    @Singleton
    class ProductViewModelFactory @Inject constructor(private val repository: ProductRepository) :
        ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>) =
            ProductViewModel(repository) as T
    }
}
