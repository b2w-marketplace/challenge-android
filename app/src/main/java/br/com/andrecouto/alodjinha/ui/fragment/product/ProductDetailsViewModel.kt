package br.com.andrecouto.alodjinha.ui.fragment.product

import br.com.andrecouto.alodjinha.domain.model.common.Status
import br.com.andrecouto.alodjinha.domain.model.lodjinha.Product
import br.com.andrecouto.alodjinha.domain.model.response.ErrorResponse
import br.com.andrecouto.alodjinha.domain.model.response.SuccessResponse
import br.com.andrecouto.alodjinha.domain.model.response.UseCaseResponse
import br.com.andrecouto.alodjinha.domain.model.response.UseCaseResponseNothing
import br.com.andrecouto.alodjinha.domain.usecase.product.GetProductDetails
import br.com.andrecouto.alodjinha.domain.usecase.product.RetainProduct
import br.com.andrecouto.alodjinha.ui.base.BaseViewModel
import br.com.andrecouto.alodjinha.util.arch.SingleLiveEvent
import br.com.andrecouto.alodjinha.util.connectivity.BaseConnectionManager
import br.com.andrecouto.alodjinha.util.livedata.NonNullLiveData
import io.reactivex.Completable
import javax.inject.Inject

class ProductDetailsViewModel @Inject constructor(
        connectionManager: BaseConnectionManager,
        private val getProductDetailsUseCase: GetProductDetails,
        private val retainProductUseCase: RetainProduct
) : BaseViewModel(connectionManager) {

    private val TAG = ProductDetailsViewModel::class.java.simpleName

    val product = NonNullLiveData(Product(0,"", "",0.0f, 0.0f, ""))
    val resultRetainProduct = NonNullLiveData("")

    val status = SingleLiveEvent(Status.EMPTY)

    fun getProducts(productId : Int) {
        if (checkConnection()) {
            status.value = Status.LOADING
            getProductDetailsUseCase.execute(compositeDisposable, this::getProductDetails, GetProductDetails.Params(productId))
        } else {
            status.value = Status.NO_CONNECTION
        }
    }

    fun retainProduct(productId : Int) {
        if (checkConnection()) {
            status.value = Status.LOADING
            retainProductUseCase.execute(compositeDisposable, this::retainProductReponse, RetainProduct.Params(productId))
        } else {
            status.value = Status.NO_CONNECTION
        }
    }

    fun getProductDetails(response: UseCaseResponse<Product>) {
        when (response) {
            is SuccessResponse -> {
                product.value = response.value
                status.value = Status.LOADED
            }
            is ErrorResponse -> status.value = Status.FAILED
        }
    }

    fun retainProductReponse(response : UseCaseResponse<String>) {
        when (response) {
            is SuccessResponse -> {
                resultRetainProduct.value = response.value
                status.value = Status.LOADED
            }
            is ErrorResponse -> status.value = Status.FAILED
        }
    }

    fun onProductClicked(product: Product) {
        retainProduct(product.id)
    }
}