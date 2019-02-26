package br.com.andrecouto.alodjinha.ui.fragment.category

import br.com.andrecouto.alodjinha.domain.model.lodjinha.Product
import br.com.andrecouto.alodjinha.domain.model.response.SuccessResponse
import br.com.andrecouto.alodjinha.domain.model.response.UseCaseResponse
import br.com.andrecouto.alodjinha.domain.usecase.product.GetProducts
import br.com.andrecouto.alodjinha.ui.base.BaseViewModel
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

    fun getProducts(categoryId : Int) {
        getProductsUseCase.execute(compositeDisposable, this::getProducts, GetProducts.Params(categoryId))
    }

    fun getProducts(response: UseCaseResponse<List<Product>>) {
        when (response) {
            is SuccessResponse -> products.value = response.value
        }
    }

    fun onProductClicked(product: Product) {
        selectedProduct.value = product
    }
}