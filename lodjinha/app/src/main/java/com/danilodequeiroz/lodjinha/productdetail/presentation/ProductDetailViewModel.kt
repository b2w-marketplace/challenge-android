package com.danilodequeiroz.lodjinha.productdetail.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.danilodequeiroz.lodjinha.common.presentation.*
import com.danilodequeiroz.lodjinha.common.util.CONNECTION_FAULTY
import com.danilodequeiroz.lodjinha.common.util.ERROR_RESULT
import com.danilodequeiroz.lodjinha.common.util.POST_LOADING
import com.danilodequeiroz.lodjinha.common.util.SUCCESS_RESULT
import com.danilodequeiroz.lodjinha.productdetail.domain.DetailedProducViewModel
import com.danilodequeiroz.lodjinha.productdetail.domain.ProductDetailUseCase
import com.danilodequeiroz.webapi.model.post.ReserveProductPayload
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

class ProductDetailViewModel(
    private val productsUseCase: ProductDetailUseCase,
    val subscribeOnScheduler: Scheduler,
    val observeOnScheduler: Scheduler
) : ViewModel() {

    val stateProduct = MutableLiveData<SingleState>()
    val stateReserve = MutableLiveData<PostState>()
    val categoryId = MutableLiveData<Int>()
    val disposable = CompositeDisposable()

    init {
        stateProduct.value = DefaultSingleState(null)
        stateReserve.value = NotPostState(null, null)
    }


    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }


    fun pullProduct(categoryId: Int) {
        stateProduct.value = LoadingSingleState(
            stateProduct.value?.data
        )
        getProductDetail(categoryId)
    }

    fun restoreProduct() {
        stateProduct.value =
            DefaultSingleState(stateProduct.value?.data)
    }

    fun postReserve() {
        stateProduct.value?.data?.categoriaId?.let {
            stateReserve.value = PostingState(POST_LOADING, null)
            reserveProduct(it)
        }
    }

    private fun reserveProduct(productsId: Int) {
        disposable.add(
            productsUseCase.reserveProduct(productsId)
                .subscribeOn(subscribeOnScheduler)
                .observeOn(observeOnScheduler)
                .subscribe({
                    if (!it.result.equals(ERROR_RESULT)) {
                        onReserveResponseReceived(it)
                    } else {
                        onReserveErrorReceived(PostErrorState(it.mensagem, it.result))
                    }
                }, {
                    onReserveErrorReceived(PostErrorState(it.message, CONNECTION_FAULTY))
                })
        )
    }

    private fun getProductDetail(productsId: Int) {
        disposable.add(
            productsUseCase.getProduct(productsId)
                .subscribeOn(subscribeOnScheduler)
                .observeOn(observeOnScheduler)
                .subscribe({
                    onBestSellingProductsReceived(it)
                }, {
                    onProductListError(it)
                })
        )
    }

    private fun onBestSellingProductsReceived(product: DetailedProducViewModel) {
        stateProduct.value = DefaultSingleState(product)
    }

    private fun onProductListError(error: Throwable) {
        stateProduct.value =
            ErrorSingleState(
                error.message ?: "",
                stateProduct.value?.data
            )
    }

    private fun onReserveResponseReceived(payload: ReserveProductPayload) {
        if (payload.result == SUCCESS_RESULT) {
            stateReserve.value = PostSuccessState(payload.mensagem, payload.result)
        }
    }

    private fun onReserveErrorReceived(it: PostErrorState) {
        stateReserve.value = it
    }


}
