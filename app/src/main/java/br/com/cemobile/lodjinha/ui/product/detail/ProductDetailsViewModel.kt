package br.com.cemobile.lodjinha.ui.product.detail

import android.arch.lifecycle.LifecycleObserver
import br.com.cemobile.domain.FetchStrategy.ForceUpdate
import br.com.cemobile.domain.features.product.ReserveProduct
import br.com.cemobile.domain.model.Resource
import br.com.cemobile.domain.model.Result
import br.com.cemobile.lodjinha.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class ProductDetailsViewModel(
    private val useCase: ReserveProduct,
    coroutineContext: CoroutineContext = Dispatchers.Main
) : BaseViewModel<Boolean>(coroutineContext), LifecycleObserver {

    fun reserveProduct(productId: Long) {
        launchData {
            val result = useCase.reserve(productId, ForceUpdate)
            when (result) {
                is Result.Success -> showReservationResult(result.data)
                else -> {
                    val error = (result as Result.Error).exception
                    showError(error)
                }
            }
        }
    }

    private fun showReservationResult(reservationSuccess: Boolean) {
        resourceLiveData.postValue(Resource.success(reservationSuccess))
    }

    private fun showError(error: Throwable) {
        resourceLiveData.postValue(Resource.error(error))
    }

}
