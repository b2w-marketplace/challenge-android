package br.com.andrecouto.alodjinha.ui.base

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.arch.lifecycle.ViewModel
import android.util.Log
import br.com.andrecouto.alodjinha.util.connectivity.BaseConnectionManager
import br.com.andrecouto.alodjinha.util.livedata.ActivityActionLiveData
import br.com.andrecouto.alodjinha.util.livedata.FragmentActionLiveData
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel(private val connectionManager: BaseConnectionManager)
    : ViewModel(), LifecycleObserver {

    val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val activityAction = ActivityActionLiveData()
    val fragmentAction = FragmentActionLiveData()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun checkConnection() : Boolean {
        return connectionManager.isNetworkConnected()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    open fun onStart() {}

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    open fun onStop() {}
}