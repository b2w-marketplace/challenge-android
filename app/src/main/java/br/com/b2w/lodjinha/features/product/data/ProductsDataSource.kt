package br.com.b2w.lodjinha.features.product.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import br.com.b2w.lodjinha.Api
import br.com.b2w.lodjinha.features.product.Product

class ProductsDataSource(private val api: Api,
                         private val categoryId: Int,
                         private val loadingState: MutableLiveData<LoadingState>
) : PageKeyedDataSource<Int, Product>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Product>) {
        val response = api.getProducts(FIRST_PAGE, params.requestedLoadSize, categoryId).execute()
        val responseBody = response.body()
        if (response.isSuccessful && responseBody != null) {
            callback.onResult(responseBody.data, null, FIRST_PAGE + params.requestedLoadSize)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Product>) {
        setLoadingState()
        val response = api.getProducts(params.key, params.requestedLoadSize, categoryId).execute()
        val responseBody = response.body()
        if (response.isSuccessful && responseBody != null) {
            setLoadedState()
            callback.onResult(responseBody.data, params.key + params.requestedLoadSize)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Product>) {
        // Do nothing
    }

    private fun setLoadedState() {
        loadingState.postValue(LoadingState.LOADED)
    }

    private fun setLoadingState() {
        loadingState.postValue(LoadingState.LOADING)
    }

    companion object {
        const val FIRST_PAGE = 0
    }
}