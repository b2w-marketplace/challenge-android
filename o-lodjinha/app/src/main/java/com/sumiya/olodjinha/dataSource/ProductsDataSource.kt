package com.sumiya.olodjinha.dataSource

import android.arch.paging.PageKeyedDataSource
import com.sumiya.olodjinha.model.CategoryModel
import com.sumiya.olodjinha.model.ProductDataModel
import com.sumiya.olodjinha.model.ProductModel
import com.sumiya.olodjinha.service.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsDataSource(private var category: CategoryModel) : PageKeyedDataSource<Int, ProductModel>() {
    companion object {
        const val PAGE_SIZE = 20
    }

    var serviceOffset = 0

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, ProductModel>) {
        val call = APIService().product().list(serviceOffset, PAGE_SIZE, category.id)

        call.enqueue(object : Callback<ProductDataModel> {
            override fun onFailure(call: Call<ProductDataModel>?, t: Throwable?) {
                if (t != null) {
                    print(t.localizedMessage)
                }
            }

            override fun onResponse(call: Call<ProductDataModel>?, response: Response<ProductDataModel>?) {
                if (response != null) {
                    serviceOffset += PAGE_SIZE
                    callback.onResult(response.body()!!.data, null, serviceOffset)
                }
            }
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ProductModel>) {
        val call = APIService().product().list(serviceOffset, PAGE_SIZE, category.id)

        call.enqueue(object : Callback<ProductDataModel> {
            override fun onFailure(call: Call<ProductDataModel>?, t: Throwable?) {
                if (t != null) {
                    print(t.localizedMessage)
                }
            }

            override fun onResponse(call: Call<ProductDataModel>?, response: Response<ProductDataModel>?) {
                if (response != null) {
                    val key = (if (serviceOffset >= response.body()?.total ?: 0) params.key + 1 else null)?.toInt()
                    callback.onResult(response.body()!!.data, key)
                }
            }
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ProductModel>) {
        val call = APIService().product().list(serviceOffset, PAGE_SIZE, category.id)

        call.enqueue(object : Callback<ProductDataModel> {
            override fun onFailure(call: Call<ProductDataModel>?, t: Throwable?) {
                if (t != null) print(t.localizedMessage)
            }

            override fun onResponse(call: Call<ProductDataModel>?, response: Response<ProductDataModel>?) {
                if (response != null) {
                    val key = (if (params.key > 1) params.key - 1 else null)?.toInt()
                    callback.onResult(response.body()!!.data, key)
                }
            }
        })
    }
}