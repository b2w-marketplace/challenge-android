package br.com.cemobile.data.source

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PositionalDataSource
import br.com.cemobile.domain.FetchStrategy
import br.com.cemobile.domain.model.Product
import br.com.cemobile.domain.model.Result
import br.com.cemobile.domain.repository.ProductRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

class ProductPositionalDataSource(
    private val repository: ProductRepository,
    private val categoryId: Long
) : PositionalDataSource<Product>() {

    private val job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)
    private val loadingLiveData = MutableLiveData<Boolean>()

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Product>) {
        uiScope.launch {
            try {
                loadingLiveData.postValue(true)
                val result = repository.getAllProducts(
                    offset = params.requestedStartPosition,
                    limit = params.pageSize,
                    categoryId = categoryId,
                    strategy = FetchStrategy.ForceUpdate
                )
                when (result) {
                    is Result.Success -> callback.onResult(result.data, 0)
                    else -> throw RemoteDataNotFoundException()
                }

                loadingLiveData.postValue(false)
            } catch (error: Throwable) {
                Timber.e("Ocorreu um erro no load inicial! ${error.localizedMessage}")
            }
        }

    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Product>) {
        uiScope.launch {
            try {
                loadingLiveData.postValue(true)
                val result = repository.getAllProducts(
                    params.startPosition,
                    params.loadSize,
                    categoryId,
                    FetchStrategy.ForceUpdate
                )
                when (result) {
                    is Result.Success -> callback.onResult(result.data)
                    else -> throw RemoteDataNotFoundException()
                }

                loadingLiveData.postValue(false)
            } catch (error: Throwable) {
                Timber.e("Ocorreu um erro no load inicial! ${error.localizedMessage}")
            }
        }
    }

    fun getLoadingLiveData(): LiveData<Boolean> = loadingLiveData

}