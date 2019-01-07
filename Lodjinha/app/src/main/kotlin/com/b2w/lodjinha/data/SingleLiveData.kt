package com.b2w.lodjinha.data

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.support.annotation.MainThread
import timber.log.Timber

open class SingleLiveData<T> : MutableLiveData<T>() {

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<T>) {

        if (hasActiveObservers()) {
            Timber.w("Multiple observers registered, but only one will be notified of changes.")
        }

        super.observe(owner, Observer<T> {
            it?.let {
                observer.onChanged(it)
            }
        })
    }

    @MainThread
    override fun setValue(value: T?) {
        super.setValue(value)
    }
}