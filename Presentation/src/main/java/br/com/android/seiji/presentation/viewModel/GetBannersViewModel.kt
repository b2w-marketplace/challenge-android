package br.com.android.seiji.presentation.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import br.com.android.seiji.domain.interactor.banner.GetBanners
import br.com.android.seiji.domain.model.Banner
import br.com.android.seiji.presentation.mapper.BannerViewMapper
import br.com.android.seiji.presentation.model.BannerView
import br.com.android.seiji.presentation.state.Resource
import br.com.android.seiji.presentation.state.ResourceState
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class GetBannersViewModel @Inject constructor(
        private val getBanners: GetBanners,
        private val mapper: BannerViewMapper
) : ViewModel() {

    private val liveData: MutableLiveData<Resource<List<BannerView>>> = MutableLiveData()

    override fun onCleared() {
        getBanners.disposable()
        super.onCleared()
    }

    fun getBanners(): LiveData<Resource<List<BannerView>>> {
        return liveData
    }

    fun fetchBanners() {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        return getBanners.execute(BannersSubscriber())
    }

    inner class BannersSubscriber : DisposableObserver<List<Banner>>() {
        override fun onComplete() {}

        override fun onNext(t: List<Banner>) {
            liveData.postValue(
                    Resource(
                            ResourceState.SUCCESS,
                            t.map { mapper.mapToView(it) }, null
                    )
            )
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
        }
    }
}