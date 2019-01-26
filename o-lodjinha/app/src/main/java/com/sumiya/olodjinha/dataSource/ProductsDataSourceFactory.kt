package com.sumiya.olodjinha.DataSource

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import android.arch.paging.PageKeyedDataSource
import com.sumiya.olodjinha.Model.CategoryModel
import com.sumiya.olodjinha.Model.ProductModel

class ProductsDataSourceFactory(var category: CategoryModel): DataSource.Factory<Int,ProductModel>() {

    private val productLiveDataSource = MutableLiveData<PageKeyedDataSource<Int, ProductModel>>()

    override fun create(): DataSource<Int, ProductModel>? {
        val productsDataSource = ProductsDataSource(category)
        productLiveDataSource.postValue(productsDataSource)
        return productsDataSource
    }

    fun getItemLiveDataSource(): MutableLiveData<PageKeyedDataSource<Int, ProductModel>> {
        return productLiveDataSource
    }
}