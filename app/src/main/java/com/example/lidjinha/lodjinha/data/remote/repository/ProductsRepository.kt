package com.example.lidjinha.lodjinha.data.remote.repository

import android.util.Log
import com.example.lidjinha.lodjinha.R
import com.example.lidjinha.lodjinha.data.model.WSReserveResponse
import com.example.lidjinha.lodjinha.data.model.WSResponse
import com.example.lidjinha.lodjinha.data.remote.retrofit.RetrofitService
import com.example.lidjinha.lodjinha.model.Product
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.reflect.KFunction1

class ProductsRepository {

    val LIMIT_PRODUCTS_CATEGORY_REQUEST = 20

    companion object {
        val instance: ProductsRepository by lazy { ProductsRepository() }
    }

    fun getBestSellers(onBestSellersRetrieved: KFunction1<List<Product>, Unit>) {
        var callBestSellers = RetrofitService.getBestSellers().bestSellers()
        callBestSellers.enqueue(bestSellersCallback(onBestSellersRetrieved))
    }

    private fun bestSellersCallback(onBestSellersRetrieved: KFunction1<List<Product>, Unit>) = object : Callback<WSResponse<List<Product>>> {

        override fun onResponse(call: Call<WSResponse<List<Product>>>?, response: Response<WSResponse<List<Product>>>?) {
            response.apply { this?.body()?.let { it.body?.let { it1 -> onBestSellersRetrieved(it1) } } }
        }

        override fun onFailure(call: Call<WSResponse<List<Product>>>?, t: Throwable) {
            Log.d("Products failure:", t.message)
        }
    }

    fun getReserve(onReserveRetrieved: KFunction1<Int, Unit>, productId: kotlin.Int) {
        val callReserve = RetrofitService.getReserve().reserve(JSONObject(), productId)
        callReserve.enqueue(reserveCallback(onReserveRetrieved))
    }

    private fun reserveCallback(onReserveRetrieved: KFunction1<Int, Unit>) = object : Callback<WSReserveResponse> {

        override fun onResponse(call: Call<WSReserveResponse>?, response: Response<WSReserveResponse>?) {
            System.out.print(response)
            response?.apply {
                this.body()?.let {
                    if (it.result == "success") {
                        onReserveRetrieved(R.string.success)
                    } else {
                        onReserveRetrieved(R.string.error_try_again)
                    }
                }
            }
        }

        override fun onFailure(call: Call<WSReserveResponse>?, t: Throwable) {
            onReserveRetrieved(R.string.error_try_again)
        }
    }

    fun getProductsCategory(onProductsRetrieved: KFunction1<List<Product>, Unit>, categoryId: Int, page: Int) {
        val callProductsCategory = RetrofitService.getBestSellers().productsCategory(categoryId, LIMIT_PRODUCTS_CATEGORY_REQUEST, page)
        callProductsCategory.enqueue(productsCategoryCallback(onProductsRetrieved))
    }

    private fun productsCategoryCallback(onProductsRetrieved: KFunction1<List<Product>, Unit>) = object : Callback<WSResponse<List<Product>>> {
        override fun onResponse(call: Call<WSResponse<List<Product>>>, response: Response<WSResponse<List<Product>>>) {
            response.apply { this.body()?.let { it.body?.let { it1 -> onProductsRetrieved(it1) } } }
        }

        override fun onFailure(call: Call<WSResponse<List<Product>>>, t: Throwable) {
            Log.d("Products failure:", t.message)
        }
    }
}