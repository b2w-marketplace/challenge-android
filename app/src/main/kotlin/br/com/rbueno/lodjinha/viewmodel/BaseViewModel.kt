package br.com.rbueno.lodjinha.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

class BaseViewModel : ViewModel() {

    protected val errorMutableLiveData = MutableLiveData<String>()
    protected val disposables = CompositeDisposable()

    val errorLiveData: LiveData<String>
        get() = errorMutableLiveData

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}