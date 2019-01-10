package br.com.android.seiji.presentation.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import br.com.android.seiji.domain.interactor.product.GetBestSellerProducts
import br.com.android.seiji.domain.model.Product
import br.com.android.seiji.presentation.mapper.ProductViewMapper
import br.com.android.seiji.presentation.model.ProductView
import br.com.android.seiji.presentation.state.Resource
import br.com.android.seiji.presentation.state.ResourceState
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class GetBestSellerProductsViewModel @Inject constructor(
        private val getBestSellerProducts: GetBestSellerProducts,
        private val mapper: ProductViewMapper
) : ViewModel() {

    private val liveData: MutableLiveData<Resource<List<ProductView>>> = MutableLiveData()

    override fun onCleared() {
        getBestSellerProducts.disposable()
        super.onCleared()
    }

    fun getBestSellerProducts(): LiveData<Resource<List<ProductView>>> {
        return liveData
    }

    fun fetchBestSellerProducts() {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        return getBestSellerProducts.execute(GetBestSellerProductsSubscriber())
    }

    inner class GetBestSellerProductsSubscriber : DisposableObserver<List<Product>>() {
        override fun onComplete() {}

        override fun onNext(t: List<Product>) {
            liveData.postValue(
                    Resource(
                            ResourceState.SUCCESS,
                            t.map { mapper.mapToView(it) }, null
                    )
            )
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
        }
    }
}