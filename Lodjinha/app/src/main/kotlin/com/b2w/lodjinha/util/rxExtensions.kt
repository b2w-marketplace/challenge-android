package com.b2w.lodjinha.util

import android.arch.lifecycle.MutableLiveData
import android.net.NetworkInfo
import com.b2w.lodjinha.R
import com.b2w.lodjinha.data.Resource
import com.b2w.lodjinha.data.ResourceException
import com.b2w.lodjinha.data.ResourceLiveData
import com.b2w.lodjinha.ui.BaseViewModel
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import retrofit2.Response

fun <T> Observable<T>.doOnSubscribeConnectedToNetwork(
    networkInfo: NetworkInfo?,
    onSubscribe: (Disposable) -> Unit
): Observable<T> {
    return if (networkInfo == null || !networkInfo.isConnected) {
        Observable.error(
            HttpException(
                Response.error<String>(
                    StatusCode.WITHOUT_CONNECTION,
                    ResponseBody.create(null, "")
                )
            )
        )
    } else {
        return this.doOnSubscribe(onSubscribe)
    }
}

fun <T> Observable<T>.toErrorHandler() = ErrorHandlerObservable(this)

data class Retriable<T>(
    val handlerObservable: ErrorHandlerObservable<T>,
    val baseViewModel: BaseViewModel,
    val liveResource: ResourceLiveData<T>
) {
    fun retry() {
        handlerObservable.subscribeLiveData(baseViewModel, liveResource)
    }
}

data class ErrorHandlerObservable<T>(val observable: Observable<T>) {

    private var handleUnauthorized = true

    fun doNotHandleUnauthorized() = apply {
        handleUnauthorized = false
    }

    fun subscribeLiveData(viewModel: BaseViewModel, liveData: ResourceLiveData<T>, useCache: Boolean = false) {

        if (useCache && liveData.value != null && liveData.value?.data != null) return

        viewModel.lastRetryable = Retriable(this, viewModel, liveData)

        viewModel.disposables.add(
            observable
                .subscribeOn(Schedulers.io())
                .subscribe({
                    liveData.postValue(Resource.success(it))
                }, {
                    handleError(liveData, it)
                })
        )
    }

    fun subscribe(viewModel: BaseViewModel, onSuccess: (T) -> Unit, onError: (Throwable) -> Unit) {
        viewModel.disposables.add(observable.subscribe(onSuccess::invoke, onError::invoke))
    }

    private fun handleError(liveData: MutableLiveData<Resource<T>>, it: Throwable) {
        liveData.postValue(Resource.error(parseError(it)))
    }

    private fun parseError(it: Throwable) = if (it is HttpException) {
        when (it.code()) {
            StatusCode.UNAUTHORIZED ->
                ResourceException(
                    title = R.string.default_unauthorized_error_title,
                    msg = R.string.default_unauthorized_error_message,
                    code = it.code()
                )
            StatusCode.FORBIDDEN ->
                ResourceException(
                    title = R.string.default_expectation_forbidden_error_title,
                    msg = R.string.default_expectation_forbidden_error_message,
                    code = it.code()
                )
            StatusCode.NOT_FOUND ->
                ResourceException(
                    title = R.string.default_internet_not_found_error_title,
                    msg = R.string.default_internet_not_found_error_message,
                    code = it.code()
                )
            else -> ResourceException(code = it.code())
        }
    } else {
        ResourceException()
    }
}

object StatusCode {
    const val UNAUTHORIZED = 401
    const val FORBIDDEN = 403
    const val NOT_FOUND = 404
    const val WITHOUT_CONNECTION = 418
}