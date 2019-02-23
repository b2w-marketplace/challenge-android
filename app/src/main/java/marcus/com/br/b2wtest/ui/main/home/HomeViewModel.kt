package marcus.com.br.b2wtest.ui.main.home

import marcus.com.br.b2wtest.model.data.BannerResult
import marcus.com.br.b2wtest.model.service.BannerService
import marcus.com.br.b2wtest.ui.BaseViewModel
import marcus.com.br.b2wtest.util.ResourceLiveData
import marcus.com.br.b2wtest.util.toNetworkFlowable
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val bannerService: BannerService) : BaseViewModel() {

    val bannerResult = ResourceLiveData<BannerResult>()

    fun getBanners() {
        bannerService.getBanners()
            .toNetworkFlowable()
            .subscribeLiveData(this, bannerResult)
    }
}