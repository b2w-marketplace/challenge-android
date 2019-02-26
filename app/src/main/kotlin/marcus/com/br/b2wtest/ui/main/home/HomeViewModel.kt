package marcus.com.br.b2wtest.ui.main.home

import marcus.com.br.b2wtest.model.data.BannerResult
import marcus.com.br.b2wtest.model.data.CategoryResult
import marcus.com.br.b2wtest.model.data.ProductResult
import marcus.com.br.b2wtest.model.service.BannerService
import marcus.com.br.b2wtest.model.service.CategoryService
import marcus.com.br.b2wtest.model.service.ProductService
import marcus.com.br.b2wtest.ui.BaseViewModel
import marcus.com.br.b2wtest.util.ResourceLiveData
import marcus.com.br.b2wtest.util.toNetworkFlowable
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val bannerService: BannerService,
    private val categoryService: CategoryService,
    private val productService: ProductService
) : BaseViewModel() {

    val bannerResult = ResourceLiveData<BannerResult>()
    val categorieResult = ResourceLiveData<CategoryResult>()
    val productBestResult = ResourceLiveData<ProductResult>()

    fun getBanners() {
        bannerService.getBanners()
            .doFinally { loading.postValue(false) }
            .toNetworkFlowable()
            .subscribeLiveData(this, bannerResult)
    }

    fun getCategories() {
        categoryService.getCategories()
            .doFinally { loading.postValue(false) }
            .toNetworkFlowable()
            .subscribeLiveData(this, categorieResult)
    }

    fun getBestSellers() {
        productService.getBestSellers()
            .doFinally { loading.postValue(false) }
            .toNetworkFlowable()
            .subscribeLiveData(this, productBestResult)
    }
}