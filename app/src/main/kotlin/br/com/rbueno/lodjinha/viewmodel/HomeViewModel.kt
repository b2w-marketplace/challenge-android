package br.com.rbueno.lodjinha.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.rbueno.lodjinha.model.Banner
import br.com.rbueno.lodjinha.repository.HomeRepository
import br.com.rbueno.lodjinha.util.handlerLoading
import javax.inject.Inject
import javax.inject.Singleton

class HomeViewModel(private val repository: HomeRepository) : BaseViewModel() {

    private val bannerMutableData = MutableLiveData<Banner>()
    val bannerLiveData: LiveData<Banner>
        get() = bannerMutableData

    fun loadBanner() {
        disposables.add(
            repository.getBanner()
                .handlerLoading(loadingMutableLiveData)
                .subscribe({
                    bannerMutableData.postValue(it)
                }, {
                    //TODO: tratar erros especificos
                    errorMutableLiveData.postValue(it.message)
                })
        )
    }

    @Suppress("UNCHECKED_CAST")
    @Singleton
    class HomeViewModelFactory @Inject constructor(private val repository: HomeRepository) :
        ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>) =
            HomeViewModel(repository) as T
    }
}
