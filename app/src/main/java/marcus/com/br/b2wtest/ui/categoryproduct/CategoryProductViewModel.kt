package marcus.com.br.b2wtest.ui.categoryproduct

import marcus.com.br.b2wtest.model.data.ProductResult
import marcus.com.br.b2wtest.model.service.ProductService
import marcus.com.br.b2wtest.ui.BaseViewModel
import marcus.com.br.b2wtest.util.ResourceLiveData
import marcus.com.br.b2wtest.util.toNetworkFlowable
import javax.inject.Inject

class CategoryProductViewModel @Inject constructor(val productService: ProductService) : BaseViewModel() {

    val productResult = ResourceLiveData<ProductResult>()

    fun getProductsByCategory(offset: Int, categoryId: Int) {
        productService
            .getByCategory(offset, categoryId)
            .doOnSubscribe { loading.postValue(true) }
            .doFinally { loading.postValue(false) }
            .toNetworkFlowable()
            .subscribeLiveData(this, productResult)
    }
}