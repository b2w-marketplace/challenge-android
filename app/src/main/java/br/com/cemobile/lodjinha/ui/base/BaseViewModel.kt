package br.com.cemobile.lodjinha.ui.base

import android.arch.lifecycle.*
import android.arch.lifecycle.Lifecycle.Event.ON_DESTROY
import android.support.annotation.VisibleForTesting
import br.com.cemobile.domain.model.Resource
import br.com.cemobile.lodjinha.util.testing.EspressoIdlingResource
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel<T>(
    coroutineContext: CoroutineContext = Dispatchers.Main
) : ViewModel(),
    LifecycleObserver {

    private val job = SupervisorJob()
    private var uiScope = CoroutineScope(coroutineContext + job)
    protected val resourceLiveData = MutableLiveData<Resource<T>>()

    protected fun launchData(block: suspend CoroutineScope.() -> Unit): Job = uiScope.launch {
        try {
            showLoading()
            incrementIdlingResource()
            block()
            decrementIdlingResource()
        } catch (error: Throwable) {
            showError(error)
        }
    }

    @OnLifecycleEvent(ON_DESTROY)
    protected fun cancelJobs() {
        job.cancelChildren()
    }

    private fun showLoading() {
        resourceLiveData.postValue(Resource.loading())
    }

    private fun showError(error: Throwable) {
        resourceLiveData.postValue(Resource.error(error))
    }

    fun getResourceLiveData(): LiveData<Resource<T>> = resourceLiveData

    @VisibleForTesting
    fun incrementIdlingResource() {
        EspressoIdlingResource.increment()
    }

    @VisibleForTesting
    fun decrementIdlingResource() {
        if (!EspressoIdlingResource.idlingResource.isIdleNow) {
            EspressoIdlingResource.decrement()
        }
    }


}