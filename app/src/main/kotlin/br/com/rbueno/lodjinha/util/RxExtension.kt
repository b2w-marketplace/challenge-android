package br.com.rbueno.lodjinha.util

import androidx.lifecycle.MutableLiveData
import io.reactivex.Completable
import io.reactivex.Single

fun <T> Single<T>.handlerLoading(loading: MutableLiveData<Boolean>): Single<T> {
    return this
        .doOnSubscribe { loading.postValue(true) }
        .doOnError { loading.postValue(false) }
        .doOnSuccess { loading.postValue(false) }
}

fun Completable.handlerLoading(loading: MutableLiveData<Boolean>): Completable {
    return this
        .doOnSubscribe { loading.postValue(true) }
        .doOnError { loading.postValue(false) }
        .doOnComplete { loading.postValue(false) }
}