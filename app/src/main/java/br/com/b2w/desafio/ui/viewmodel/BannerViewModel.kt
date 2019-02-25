package br.com.b2w.desafio.ui.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import br.com.b2w.desafio.data.remote.BannerService
import br.com.b2w.desafio.data.remote.ServiceGenerator
import br.com.b2w.desafio.model.Banner
import br.com.b2w.desafio.model.response.LodjinhaResponse
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BannerViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var mutableLiveDataListar: MutableLiveData<LodjinhaResponse<MutableList<Banner>>>

    fun listarObservable(): MutableLiveData<LodjinhaResponse<MutableList<Banner>>> {
        if (!::mutableLiveDataListar.isInitialized) {
            mutableLiveDataListar = MutableLiveData()
        }
        return mutableLiveDataListar
    }

    fun listar() {
        val bannerService = ServiceGenerator.createService(BannerService::class.java)
        val call = bannerService.listar()
        call.enqueue(object : Callback<LodjinhaResponse<MutableList<Banner>>> {
            override fun onResponse(call: Call<LodjinhaResponse<MutableList<Banner>>>, response: Response<LodjinhaResponse<MutableList<Banner>>>) {
                if (response.isSuccessful) {
                    mutableLiveDataListar.value = response.body()
                } else {
                    val gson = Gson()
                    val type = object : TypeToken<LodjinhaResponse<MutableList<Banner>>>() {}.type
                    var errorResponse: LodjinhaResponse<MutableList<Banner>>? = gson.fromJson(response.errorBody()!!.charStream(), type)
                    mutableLiveDataListar.value = errorResponse
                }
            }

            override fun onFailure(call: Call<LodjinhaResponse<MutableList<Banner>>>, t: Throwable) {
                val lodjinhaResponse: LodjinhaResponse<MutableList<Banner>> = LodjinhaResponse(
                    null, null, null, null, t.message
                )
                mutableLiveDataListar.value = lodjinhaResponse
            }
        })
    }
}