package br.com.rbueno.lodjinha.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.rbueno.lodjinha.model.Banner
import br.com.rbueno.lodjinha.model.Category
import br.com.rbueno.lodjinha.model.ProductList
import br.com.rbueno.lodjinha.repository.HomeRepository
import br.com.rbueno.lodjinha.util.handlerLoading
import javax.inject.Inject
import javax.inject.Singleton

class HomeViewModel(private val repository: HomeRepository) : BaseViewModel() {

    private val bannerMutableData = MutableLiveData<Banner>()
    private val categoryMutableData = MutableLiveData<Category>()
    private val mostSoldMutableData = MutableLiveData<ProductList>()

    val bannerLiveData: LiveData<Banner>
        get() = bannerMutableData

    val categoryLiveData: LiveData<Category>
        get() = categoryMutableData

    val mostSoldLiveData: LiveData<ProductList>
        get() = mostSoldMutableData

    fun loadBanner() {
        disposables.add(
            repository.getBanner()
                .handlerLoading(loadingMutableLiveData)
                .subscribe({
                    bannerMutableData.postValue(it)
                }, {
                    handleError(it)
                })
        )
    }

    fun loadCategory() {
        disposables.add(
            repository.getCategory()
                .handlerLoading(loadingMutableLiveData)
                .subscribe({
                    categoryMutableData.postValue(it)
                   // categoryMutableData.postValue(null)
                }, {
                    handleError(it)
                })
        )
    }

    fun loadMostSoldProducts() {
        disposables.add(
            repository.getProductsMostSold()
                .handlerLoading(loadingMutableLiveData)
                .subscribe({
                    mostSoldMutableData.postValue(it)
                }, {
                    handleError(it)
                })
        )
    }

    fun clearCategoryLiveData() {
        categoryMutableData.value = null
    }

    fun clearBannerLiveData() {
        bannerMutableData.value = null
    }

    fun clearMostSoldLiveData() {
        mostSoldMutableData.value = null
    }

    @Suppress("UNCHECKED_CAST")
    @Singleton
    class HomeViewModelFactory @Inject constructor(private val repository: HomeRepository) :
        ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>) =
            HomeViewModel(repository) as T
    }
}
