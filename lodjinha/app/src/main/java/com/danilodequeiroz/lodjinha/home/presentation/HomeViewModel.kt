package com.danilodequeiroz.lodjinha.home.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.danilodequeiroz.lodjinha.common.presentation.DefaultState
import com.danilodequeiroz.lodjinha.common.presentation.ErrorState
import com.danilodequeiroz.lodjinha.common.presentation.ListState
import com.danilodequeiroz.lodjinha.common.presentation.LoadingState
import com.danilodequeiroz.lodjinha.common.util.currentData
import com.danilodequeiroz.lodjinha.common.util.didLoadedAllItems
import com.danilodequeiroz.lodjinha.home.domain.*
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

class HomeViewModel(
        private val homeUseCase: HomeUseCase,
        val subscribeOnScheduler: Scheduler,
        val observeOnScheduler: Scheduler
) : ViewModel() {

    val stateBanners = MutableLiveData<ListState>()
    val stateProductCategories = MutableLiveData<ListState>()
    val stateBestSellingProducts = MutableLiveData<ListState>()
    private val disposable = CompositeDisposable()

    init {
        stateBanners.value = DefaultState(emptyList(), false)
        stateProductCategories.value = DefaultState(emptyList(), false)
        stateBestSellingProducts.value = DefaultState(emptyList(), false)
    }


    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }

    fun initBanners() {
        stateBanners.value = LoadingState(stateBanners.currentData())
        getBanners()
    }

    fun initProductCategories() {
        stateProductCategories.value =
                LoadingState(stateProductCategories.currentData())
        getProductCategories()
    }

    fun initBestSellingProducts() {
        stateBestSellingProducts.value =
                LoadingState(stateBestSellingProducts.currentData())
        getBestSellingProducts()
    }

    fun restoreHome() {
        stateBanners.value = DefaultState(stateBanners.currentData())
        stateProductCategories.value =
                DefaultState(stateProductCategories.currentData())
        stateBestSellingProducts.value =
                DefaultState(stateBestSellingProducts.currentData())
    }


    private fun getBanners() {
        disposable.add(
                homeUseCase.getBanners()
                        .subscribeOn(subscribeOnScheduler)
                        .observeOn(observeOnScheduler)
                        .subscribe({ onBannersReceived(it) }, { onError(it, stateBanners) })
        )
    }

    private fun onBannersReceived(banners: MutableList<BannerViewModel>) {
        val currentList = stateBanners.currentData()
        currentList.addAll(banners)
        stateBanners.value = DefaultState(currentList)
    }

    private fun getProductCategories() {
        disposable.add(
                homeUseCase.getProductCategories()
                        .subscribeOn(subscribeOnScheduler)
                        .observeOn(observeOnScheduler)
                        .subscribe({ onCategoriesReceived(it) }, { onError(it, stateProductCategories) })
        )
    }

    private fun onCategoriesReceived(productCategories: MutableList<ProductCategoryViewModel>) {
        val currentList = stateProductCategories.currentData()
        currentList.addAll(productCategories)
        stateProductCategories.value = DefaultState(currentList)
    }

    private fun getBestSellingProducts() {
        disposable.add(
                homeUseCase.getAllBestSellingProducts()
                        .subscribeOn(subscribeOnScheduler)
                        .observeOn(observeOnScheduler)
                        .subscribe({ onBestSellingProductsReceived(it) }, { onError(it, stateBestSellingProducts) })
        )
    }

    private fun onBestSellingProductsReceived(bestSellingProducts: MutableList<ProductViewModel>) {
        val currentList = stateBestSellingProducts.currentData()
        currentList.addAll(bestSellingProducts)
        stateBestSellingProducts.value = DefaultState(currentList)
    }

    private fun onError(error: Throwable, homeSectionLiveData: MutableLiveData<ListState>) {
        val didLoadedAllItems = homeSectionLiveData.didLoadedAllItems()
        val currentData = homeSectionLiveData.currentData()
        homeSectionLiveData.value =
                ErrorState(
                        error.message ?: "",
                        currentData,
                        didLoadedAllItems,
                        0
                )
    }

}


