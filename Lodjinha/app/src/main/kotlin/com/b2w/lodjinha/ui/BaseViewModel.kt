package com.b2w.lodjinha.ui

import android.arch.lifecycle.ViewModel
import com.b2w.lodjinha.data.SingleLiveData
import com.b2w.lodjinha.util.Retriable
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    var lastRetryable: Retriable<*>? = null

    var disposables = CompositeDisposable()

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}