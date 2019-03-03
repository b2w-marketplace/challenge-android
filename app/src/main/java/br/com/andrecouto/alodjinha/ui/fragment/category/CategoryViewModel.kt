package br.com.andrecouto.alodjinha.ui.fragment.category

import br.com.andrecouto.alodjinha.domain.model.common.Status
import br.com.andrecouto.alodjinha.domain.model.lodjinha.Product
import br.com.andrecouto.alodjinha.domain.model.response.ErrorResponse
import br.com.andrecouto.alodjinha.domain.model.response.SuccessResponse
import br.com.andrecouto.alodjinha.domain.model.response.UseCaseResponse
import br.com.andrecouto.alodjinha.domain.usecase.product.GetProducts
import br.com.andrecouto.alodjinha.ui.base.BaseViewModel
import br.com.andrecouto.alodjinha.util.arch.SingleLiveEvent
import br.com.andrecouto.alodjinha.util.connectivity.BaseConnectionManager
import br.com.andrecouto.alodjinha.util.livedata.NonNullLiveData
import javax.inject.Inject

class CategoryViewModel @Inject constructor(
        connectionManager: BaseConnectionManager,
        private val getProductsUseCase: GetProducts
) : BaseViewModel(connectionManager) {

    private val TAG = CategoryViewModel::class.java.simpleName

    val products = NonNullLiveData<List<Product>>(emptyList())
    val selectedProduct = NonNullLiveData(Product(0, "", "", 0.0f, 0.0f, ""))

    val statusProducts = SingleLiveEvent(Status.EMPTY)

    fun getProducts(categoryId : Int) {
        if (checkConnection()) {
            statusProducts.value = Status.LOADING
            getProductsUseCase.execute(compositeDisposable, this::getProducts, GetProducts.Params(categoryId))
        } else {
            statusProducts.value = Status.NO_CONNECTION
        }
    }

    fun getProducts(response: UseCaseResponse<List<Product>>) {
        when (response) {
            is SuccessResponse -> {
                if (response.value.isEmpty()) {
                    statusProducts.value = Status.EMPTY_LIST
                } else {
                    statusProducts.value = Status.LOADED
                    products.value = response.value
                }
            }
            is ErrorResponse -> statusProducts.value = Status.FAILED
        }
    }

    fun onProductClicked(product: Product) {
        selectedProduct.value = product
    }
}