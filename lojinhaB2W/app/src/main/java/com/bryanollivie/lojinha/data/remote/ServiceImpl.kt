package com.bryanollivie.lojinha.data.remote

import android.util.Log
import com.bryanollivie.lojinha.data.model.ReturnBase
import com.bryanollivie.lojinha.data.network.RetrofitEndpoint
import com.bryanollivie.lojinha.data.network.RetrofitInit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ServiceImpl : ServiceApi {

    internal var mRetrofit: RetrofitEndpoint

    init {
        mRetrofit = RetrofitInit.client!!.create(RetrofitEndpoint::class.java)
    }

    override fun bannerFindAll(callback: ServiceApi.ServiceCallback<ReturnBase>) {

        val call = mRetrofit.bannerFindAll()
        call.enqueue(object : Callback<ReturnBase> {

            override fun onResponse(call: Call<ReturnBase>?, response: Response<ReturnBase>?) {

                if (response != null) {
                    if (response.code() == 200) {
                        val reponse = response.body()
                        if (reponse != null) {
                            callback.onLoaded(reponse)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ReturnBase>?, t: Throwable?) {
                Log.e("Erro", "Erro Retorno" + t)
            }
        })
    }

    override fun categoriaFindAll(callback: ServiceApi.ServiceCallback<ReturnBase>) {

        val call = mRetrofit.categoriaFindAll()
        call.enqueue(object : Callback<ReturnBase> {

            override fun onResponse(call: Call<ReturnBase>?, response: Response<ReturnBase>?) {

                if (response != null) {
                    if (response.code() == 200) {
                        val reponse = response.body()
                        if (reponse != null) {
                            callback.onLoaded(reponse)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ReturnBase>?, t: Throwable?) {
                Log.e("Erro", "Erro Retorno" + t)
            }
        })
    }

    override fun produtoFindByCategoria(
        categoriaId: Int,
        offset: Int,
        limit: Int,
        callback: ServiceApi.ServiceCallback<ReturnBase>
    ) {

        val call = mRetrofit.produtoFindByCategoria(categoriaId, offset, limit)
        call.enqueue(object : Callback<ReturnBase> {

            override fun onResponse(call: Call<ReturnBase>?, response: Response<ReturnBase>?) {

                if (response != null) {
                    if (response.code() == 200) {
                        val reponse = response.body()
                        if (reponse != null) {
                            callback.onLoaded(reponse)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ReturnBase>?, t: Throwable?) {
                Log.e("Erro", "Erro Retorno" + t)
            }
        })
    }

    override fun produtoFindById(
        produtoId: Int,
        callback: ServiceApi.ServiceCallback<ReturnBase>
    ) {
        val call = mRetrofit.produtoFindById(produtoId)
        call.enqueue(object : Callback<ReturnBase> {

            override fun onResponse(call: Call<ReturnBase>?, response: Response<ReturnBase>?) {

                if (response != null) {
                    if (response.code() == 200) {
                        val reponse = response.body()
                        if (reponse != null) {
                            callback.onLoaded(reponse)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ReturnBase>?, t: Throwable?) {
                Log.e("Erro", "Erro Retorno" + t)
            }
        })
    }

    override fun reservarProdutoById(produtoId: Int, callback: ServiceApi.ServiceCallback<ReturnBase>) {
        val call = mRetrofit.reservarProdutoById(produtoId)
        call.enqueue(object : Callback<ReturnBase> {

            override fun onResponse(call: Call<ReturnBase>?, response: Response<ReturnBase>?) {

                if (response != null) {
                    if (response.code() == 200) {
                        val reponse = response.body()
                        if (reponse != null) {
                            callback.onLoaded(reponse)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ReturnBase>?, t: Throwable?) {
                Log.e("Erro", "Erro Retorno" + t)
            }
        })
    }

    override fun produtoMaisVendidos(callback: ServiceApi.ServiceCallback<ReturnBase>) {

        val call = mRetrofit.produtoMaisVendidos()
        call.enqueue(object : Callback<ReturnBase> {

            override fun onResponse(call: Call<ReturnBase>?, response: Response<ReturnBase>?) {

                if (response != null) {
                    if (response.code() == 200) {
                        val reponse = response.body()
                        if (reponse != null) {
                            callback.onLoaded(reponse)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ReturnBase>?, t: Throwable?) {
                Log.e("Erro", "Erro Retorno" + t)
            }
        })
    }

}

