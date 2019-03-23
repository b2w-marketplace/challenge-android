package br.com.rbueno.lodjinha.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.rbueno.lodjinha.model.Banner
import br.com.rbueno.lodjinha.model.Category
import br.com.rbueno.lodjinha.model.Home
import br.com.rbueno.lodjinha.model.ProductList
import br.com.rbueno.lodjinha.repository.HomeRepository
import br.com.rbueno.lodjinha.util.handlerLoading
import io.reactivex.Single
import io.reactivex.functions.Function3
import javax.inject.Inject
import javax.inject.Singleton

class HomeViewModel(private val repository: HomeRepository) : BaseViewModel() {
    private val homeMutableLiveData = MutableLiveData<Home>()

    val homeLiveData: LiveData<Home>
        get() = homeMutableLiveData


    fun loadHome() {
        disposables.add(
            Single.zip<Banner, Category, ProductList, Home>(
                repository.getBanner(),
                repository.getCategory(),
                repository.getProductsMostSold(),
                Function3 { banner, category, productList -> Home(banner, category, productList) }
            ).handlerLoading(loadingMutableLiveData)
                .subscribe({
                    homeMutableLiveData.postValue(it)
                }, {
                    handleError(it)
                })
        )
    }

    fun clearHomeLiveData() {
        homeMutableLiveData.value = null
    }

    @Suppress("UNCHECKED_CAST")
    @Singleton
    class HomeViewModelFactory @Inject constructor(private val repository: HomeRepository) :
        ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>) =
            HomeViewModel(repository) as T
    }
}
