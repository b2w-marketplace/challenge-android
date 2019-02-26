package br.com.andrecouto.alodjinha.ui.fragment.product

import br.com.andrecouto.alodjinha.domain.model.lodjinha.Product
import br.com.andrecouto.alodjinha.domain.model.response.SuccessResponse
import br.com.andrecouto.alodjinha.domain.model.response.UseCaseResponse
import br.com.andrecouto.alodjinha.domain.model.response.UseCaseResponseNothing
import br.com.andrecouto.alodjinha.domain.usecase.product.GetProductDetails
import br.com.andrecouto.alodjinha.domain.usecase.product.RetainProduct
import br.com.andrecouto.alodjinha.ui.base.BaseViewModel
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
    var resultRetainProduct = NonNullLiveData("")

    fun getProducts(productId : Int) {
        getProductDetailsUseCase.execute(compositeDisposable, this::getProductDetails, GetProductDetails.Params(productId))
    }

    fun retainProduct(productId : Int) {
        retainProductUseCase.execute(compositeDisposable, this::retainProductReponse, RetainProduct.Params(productId))
    }

    fun getProductDetails(response: UseCaseResponse<Product>) {
        when (response) {
            is SuccessResponse -> product.value = response.value
        }
    }

    fun retainProductReponse(response : UseCaseResponse<String>) {
        when (response) {
            is SuccessResponse -> resultRetainProduct.value = response.value
        }
    }

    fun onProductClicked(product: Product) {
        retainProduct(product.id)
    }
}