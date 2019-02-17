package br.com.b2w.lodjinha.features.product.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import br.com.b2w.lodjinha.features.product.Product
import br.com.b2w.lodjinha.features.product.data.LoadingState
import br.com.b2w.lodjinha.features.product.data.ProductsFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductsViewModel(private val productsFactory: ProductsFactory,
                        val loadingState: MutableLiveData<LoadingState>
) : ViewModel() {

    suspend fun getProducts(categoryId: Int): LiveData<PagedList<Product>> = withContext(Dispatchers.IO) {
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(LOAD_SIZE_HINT)
            .setPageSize(PAGE_SIZE)
            .build()
        LivePagedListBuilder(productsFactory.apply { this.categoryId = categoryId }, pagedListConfig).build()
    }

    companion object {
        const val LOAD_SIZE_HINT = 20
        const val PAGE_SIZE = 20
    }
}