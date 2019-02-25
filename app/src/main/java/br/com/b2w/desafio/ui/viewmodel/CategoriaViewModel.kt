package br.com.b2w.desafio.ui.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import br.com.b2w.desafio.data.remote.CategoriaService
import br.com.b2w.desafio.data.remote.ServiceGenerator
import br.com.b2w.desafio.model.Categoria
import br.com.b2w.desafio.model.response.LodjinhaResponse
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoriaViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var mutableLiveDataListar: MutableLiveData<LodjinhaResponse<MutableList<Categoria>>>

    fun listarObservable(): MutableLiveData<LodjinhaResponse<MutableList<Categoria>>> {
        if (!::mutableLiveDataListar.isInitialized) {
            mutableLiveDataListar = MutableLiveData()
        }
        return mutableLiveDataListar
    }

    fun listar() {
        val categoriaService = ServiceGenerator.createService(CategoriaService::class.java)
        val call = categoriaService.listar()
        call.enqueue(object : Callback<LodjinhaResponse<MutableList<Categoria>>> {
            override fun onResponse(call: Call<LodjinhaResponse<MutableList<Categoria>>>, response: Response<LodjinhaResponse<MutableList<Categoria>>>) {
                if (response.isSuccessful) {
                    mutableLiveDataListar.value = response.body()
                } else {
                    val gson = Gson()
                    val type = object : TypeToken<LodjinhaResponse<MutableList<Categoria>>>() {}.type
                    var errorResponse: LodjinhaResponse<MutableList<Categoria>>? = gson.fromJson(response.errorBody()!!.charStream(), type)
                    mutableLiveDataListar.value = errorResponse
                }
            }

            override fun onFailure(call: Call<LodjinhaResponse<MutableList<Categoria>>>, t: Throwable) {
                val lodjinhaResponse: LodjinhaResponse<MutableList<Categoria>> = LodjinhaResponse(
                    null, null, null, null, t.message
                )
                mutableLiveDataListar.value = lodjinhaResponse
            }
        })
    }
}