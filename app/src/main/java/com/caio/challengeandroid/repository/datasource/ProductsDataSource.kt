package com.caio.challengeandroid.repository.datasource

import androidx.paging.PageKeyedDataSource
import com.caio.lodjinha.repository.entity.Product
import com.caio.lodjinha.repository.remote.ProductREST

class ProductsDataSource(
    private val api: ProductREST,
    private val categoryId: Int):PageKeyedDataSource<Int, Product>() {

    private val FIRST_OFFSET = 0

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Product>) {
        val response = api.getProductsByCategory(FIRST_OFFSET, params.requestedLoadSize, categoryId).execute()
        val responseBody = response.body()
        if (response.isSuccessful && responseBody != null) {
            callback.onResult(responseBody.data, null, FIRST_OFFSET + params.requestedLoadSize)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Product>) {
        val response = api.getProductsByCategory(params.key, params.requestedLoadSize, categoryId).execute()
        val responseBody = response.body()
        if (response.isSuccessful && responseBody != null) {
            callback.onResult(responseBody.data, params.key + params.requestedLoadSize)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Product>) {
    }

}