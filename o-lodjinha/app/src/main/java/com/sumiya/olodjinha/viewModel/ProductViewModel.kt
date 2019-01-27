package com.sumiya.olodjinha.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PageKeyedDataSource
import android.arch.paging.PagedList
import com.sumiya.olodjinha.dataSource.ProductsDataSource
import com.sumiya.olodjinha.dataSource.ProductsDataSourceFactory
import com.sumiya.olodjinha.model.CategoryModel
import com.sumiya.olodjinha.model.ProductModel

class ProductViewModel(category: CategoryModel) : ViewModel() {

    var itemPagedList: LiveData<PagedList<ProductModel>>
    var liveDataSource: LiveData<PageKeyedDataSource<Int, ProductModel>>

    init {
        val itemDataSourceFactory = ProductsDataSourceFactory(category)
        liveDataSource = itemDataSourceFactory.getItemLiveDataSource()

        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(ProductsDataSource.PAGE_SIZE)
                .build()

        itemPagedList = LivePagedListBuilder(itemDataSourceFactory, config).build()
    }
}