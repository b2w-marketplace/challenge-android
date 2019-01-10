package br.com.android.seiji.presentation.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import br.com.android.seiji.domain.interactor.product.PostProductReservation
import br.com.android.seiji.presentation.state.Resource
import br.com.android.seiji.presentation.state.ResourceState
import io.reactivex.observers.DisposableCompletableObserver
import javax.inject.Inject

class PostProductReservationViewModel @Inject constructor(
        private val postProductReservation: PostProductReservation
) : ViewModel() {

    private val liveData: MutableLiveData<Resource<Any>> = MutableLiveData()

    override fun onCleared() {
        postProductReservation.disposable()
        super.onCleared()
    }

    fun getPostProductReservation(): LiveData<Resource<Any>> {
        return liveData
    }

    fun fetchPostProductReservation(productId: Int) {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        return postProductReservation.execute(PostProductReservationSubscriber(),
                PostProductReservation.Params.forProduct(productId))
    }

    inner class PostProductReservationSubscriber : DisposableCompletableObserver() {
        override fun onComplete() {
            liveData.postValue(Resource(ResourceState.SUCCESS, null, null))
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
        }
    }
}