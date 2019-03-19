package com.danilodequeiroz.lodjinha.productlist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.danilodequeiroz.lodjinha.common.presentation.*
import com.danilodequeiroz.lodjinha.common.util.currentData
import com.danilodequeiroz.lodjinha.common.util.didLoadedAllItems
import com.danilodequeiroz.lodjinha.home.domain.ProductViewModel
import com.danilodequeiroz.lodjinha.productlist.domain.PAGINATION_INTERVAL
import com.danilodequeiroz.lodjinha.productlist.domain.ProductsListUseCase
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

class ProductListViewModel(
    private val productsListUseCase: ProductsListUseCase,
    val subscribeOnScheduler: Scheduler,
    val observeOnScheduler: Scheduler
) : ViewModel() {

    val stateProductsList = MutableLiveData<ListState>()
    val categoryId = MutableLiveData<Int>()
    val disposable = CompositeDisposable()

    init {
        stateProductsList.value = DefaultState(emptyList())
    }


    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }


    fun updateProducts(categoryId: Int) {
        val lastPaginationReference = obtainLastPaginationReferenceValue()
        stateProductsList.value = if (lastPaginationReference == 0)
            LoadingState(
                stateProductsList.currentData(),
                false,
                lastPaginationReference
            )
        else
            PaginatingState(
                stateProductsList.currentData(),
                false,
                lastPaginationReference
            )
        getBestSellingProducts(categoryId, lastPaginationReference, lastPaginationReference + PAGINATION_INTERVAL)
    }

    fun restoreProducts() {
        val pageNum = obtainLastPaginationReferenceValue()
        stateProductsList.value =
            DefaultState(
                stateProductsList.currentData(),
                false,
                pageNum
            )
    }


    private fun getBestSellingProducts(categoryId: Int, offset: Int, limit: Int) {
        disposable.add(
            productsListUseCase.getProducts(categoryId, offset, limit)
                .subscribeOn(subscribeOnScheduler)
                .observeOn(observeOnScheduler)
                .subscribe({ onBestSellingProductsReceived(it) }, { onError(it, stateProductsList) })
        )
    }

    private fun onBestSellingProductsReceived(products: MutableList<ProductViewModel>) {
        val currentList = stateProductsList.currentData()
        val areAllItemsLoaded = products.isEmpty()
        currentList.addAll(products)
        if (productsListUseCase.getLastOffset() == 0 && areAllItemsLoaded) {
            stateProductsList.value = EmptyListState()
        }else {
            stateProductsList.value = DefaultState(
                    currentList,
                    areAllItemsLoaded,
                    productsListUseCase.getLastOffset()
            )
        }
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

    private fun obtainLastPaginationReferenceValue() = stateProductsList.value?.pageNum ?: 0

}
