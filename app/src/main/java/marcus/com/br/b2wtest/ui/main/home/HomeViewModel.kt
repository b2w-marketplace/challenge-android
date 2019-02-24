package marcus.com.br.b2wtest.ui.main.home

import marcus.com.br.b2wtest.model.data.BannerResult
import marcus.com.br.b2wtest.model.data.CategoryResult
import marcus.com.br.b2wtest.model.service.BannerService
import marcus.com.br.b2wtest.model.service.CategoryService
import marcus.com.br.b2wtest.ui.BaseViewModel
import marcus.com.br.b2wtest.util.ResourceLiveData
import marcus.com.br.b2wtest.util.toNetworkFlowable
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val bannerService: BannerService,
    private val categoryService: CategoryService
) : BaseViewModel() {

    val bannerResult = ResourceLiveData<BannerResult>()
    val categorieResult = ResourceLiveData<CategoryResult>()

    fun getBanners() {
        bannerService.getBanners()
            .toNetworkFlowable()
            .subscribeLiveData(this, bannerResult)
    }

    fun getCategories() {
        categoryService.getCategories()
            .toNetworkFlowable()
            .subscribeLiveData(this, categorieResult)
    }
}