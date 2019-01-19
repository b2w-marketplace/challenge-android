package br.com.cemobile.data.source

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PagedList
import android.support.annotation.MainThread
import android.util.Log
import br.com.cemobile.domain.model.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

class ProductBoundaryCallback(
    private val remoteDataSource: ProductDataSource,
    private val localDataSource: ProductDataSource,
    private val categoryId: Long,
    private val pageSize: Int = 20
) : PagedList.BoundaryCallback<Product>() {

    // keep the last requested page.
    // When the request is successful, increment the page number.
    private var lastRequestedOffset = 0
    private var isAllProductsLoaded = false
    private val job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)
    val loadingLiveData = MutableLiveData<Boolean>()

    /**
     * Database returned 0 items. We should query the backend for more items.
     */
    @MainThread
    override fun onZeroItemsLoaded() {
        requestAndSave(categoryId, pageSize)
    }

    /**
     * User reached to the end of the list.
     */
    @MainThread
    override fun onItemAtEndLoaded(itemAtEnd: Product) {
        // ignored, since we do nothing when end of the list is reached
        Timber.d("[onItemAtEndLoaded]isAllProductsLoaded=$isAllProductsLoaded")
        requestAndSave(categoryId, pageSize)
    }


    override fun onItemAtFrontLoaded(itemAtFront: Product) {
        // ignored, since we only ever append to what's in the DB
    }

    private fun requestAndSave(categoryId: Long, pageSize: Int) {
        uiScope.launch {
            loadingLiveData.postValue(true)
            val result = remoteDataSource.getAllProducts(0, pageSize, categoryId)

            if (result is Result.Success) {
                val products = result.data
                insertProductsIntoDatabase(products)
                isAllProductsLoaded = products.size < pageSize
            }
            loadingLiveData.postValue(false)
        }
    }

    /**
     * Every time it gets new items, boundary callback simply inserts them into the DB and
     * paging library takes care of refreshing the list if necessary.
     */
    private fun insertProductsIntoDatabase(products: List<Product>) {
        localDataSource.save(products)
    }

    // https://codelabs.developers.google.com/codelabs/android-paging/index.html?index=..%2F..%2Findex#8

}