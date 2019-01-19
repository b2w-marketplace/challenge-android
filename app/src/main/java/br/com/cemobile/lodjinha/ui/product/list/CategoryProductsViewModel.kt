package br.com.cemobile.lodjinha.ui.product.list

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import br.com.cemobile.data.source.ProductPositionalDataSource
import br.com.cemobile.data.source.ProductPositionalDataSourceFactory
import br.com.cemobile.domain.model.Product
import br.com.cemobile.domain.repository.ProductRepository
import br.com.cemobile.lodjinha.util.ITEMS_PER_PAGE

class CategoryProductsViewModel(
    private val categoryId: Long,
    private val repository: ProductRepository
) : ViewModel() {

    private lateinit var productsLiveData: LiveData<PagedList<Product>>
    private lateinit var loadingLiveData: LiveData<Boolean>

    init {
        setupPaging()
    }

    private fun setupPaging() {
        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(ITEMS_PER_PAGE)
                .setPageSize(ITEMS_PER_PAGE)
                .build()

        val dataSourceFactory = ProductPositionalDataSourceFactory(repository, categoryId)

        productsLiveData = LivePagedListBuilder<Int, Product>(dataSourceFactory, config).build()
        loadingLiveData = Transformations.switchMap(
            dataSourceFactory.getProductDataSourceLiveData(),
            ProductPositionalDataSource::getLoadingLiveData
        )
    }

    fun getLoadingLiveData(): LiveData<Boolean> = loadingLiveData

    fun getProductsLiveData() = productsLiveData

}