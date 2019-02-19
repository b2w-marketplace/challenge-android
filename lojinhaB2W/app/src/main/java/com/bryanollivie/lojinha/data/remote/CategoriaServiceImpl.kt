package com.bryanollivie.lojinha.data.remote

import android.util.Log
import com.bryanollivie.lojinha.data.model.ReturnBase
import com.bryanollivie.lojinha.data.network.RetrofitEndpoint
import com.bryanollivie.lojinha.data.network.RetrofitInit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoriaServiceImpl : CategoriaServiceApi {

    internal var mRetrofit: RetrofitEndpoint

    init {
        mRetrofit = RetrofitInit.client!!.create(RetrofitEndpoint::class.java)
    }

    override fun findAll(callback: CategoriaServiceApi.ServiceCallback<ReturnBase>) {

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

}

