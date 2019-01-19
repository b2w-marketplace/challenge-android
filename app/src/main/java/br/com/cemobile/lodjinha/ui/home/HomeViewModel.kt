package br.com.cemobile.lodjinha.ui.home

import android.arch.lifecycle.LifecycleObserver
import android.support.annotation.VisibleForTesting
import br.com.cemobile.domain.FetchStrategy
import br.com.cemobile.domain.features.homedata.GetHomeData
import br.com.cemobile.domain.model.HomeData
import br.com.cemobile.domain.model.Resource
import br.com.cemobile.domain.model.Result
import br.com.cemobile.lodjinha.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class HomeViewModel(
    private val useCase: GetHomeData,
    coroutineContext: CoroutineContext = Dispatchers.Main
) : BaseViewModel<HomeData>(coroutineContext), LifecycleObserver {

    fun loadHomeDataInformation() {
        launchData {
            val result = useCase.getHomeDataInformation(FetchStrategy.ForceUpdate)
            when (result) {
                is Result.Success -> showHomeData(result.data)
                else -> {
                    val error = (result as Result.Error).exception
                    showError(error)
                }
            }
        }
    }

    fun onTryAgainClicked() {
        loadHomeDataInformation()
    }

    private fun showHomeData(homeData: HomeData) {
        resourceLiveData.postValue(Resource.success(homeData))
    }

    private fun showError(error: Throwable) {
        resourceLiveData.postValue(Resource.error(error))
    }

    @VisibleForTesting
    fun getHomeDataInformation() {
        loadHomeDataInformation()
    }

}