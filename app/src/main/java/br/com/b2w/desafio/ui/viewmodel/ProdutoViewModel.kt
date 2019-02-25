package br.com.b2w.desafio.ui.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import br.com.b2w.desafio.data.remote.ProdutoService
import br.com.b2w.desafio.data.remote.ServiceGenerator
import br.com.b2w.desafio.model.produto.Produto
import br.com.b2w.desafio.model.response.LodjinhaResponse
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProdutoViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var mutableLiveDataListar: MutableLiveData<LodjinhaResponse<MutableList<Produto>>>
    private lateinit var mutableLiveDataListarMaisVendidos: MutableLiveData<LodjinhaResponse<MutableList<Produto>>>
    private lateinit var mutableLiveDataGetById: MutableLiveData<LodjinhaResponse<Produto>>
    private lateinit var mutableLiveDataPostById: MutableLiveData<LodjinhaResponse<Produto>>

    fun listarObservable(): MutableLiveData<LodjinhaResponse<MutableList<Produto>>> {
        if (!::mutableLiveDataListar.isInitialized) {
            mutableLiveDataListar = MutableLiveData()
        }
        return mutableLiveDataListar
    }

    fun listarMaisVendidosObservable(): MutableLiveData<LodjinhaResponse<MutableList<Produto>>> {
        if (!::mutableLiveDataListarMaisVendidos.isInitialized) {
            mutableLiveDataListarMaisVendidos = MutableLiveData()
        }
        return mutableLiveDataListarMaisVendidos
    }

    fun getByIdObservable(): MutableLiveData<LodjinhaResponse<Produto>> {
        if (!::mutableLiveDataGetById.isInitialized) {
            mutableLiveDataGetById = MutableLiveData()
        }
        return mutableLiveDataGetById
    }

    fun getPostByIdObservable(): MutableLiveData<LodjinhaResponse<Produto>> {
        if (!::mutableLiveDataPostById.isInitialized) {
            mutableLiveDataPostById = MutableLiveData()
        }
        return mutableLiveDataPostById
    }

    fun listar(offset: Int, limit: Int, categoriaId: Int) {
        val produtoService = ServiceGenerator.createService(ProdutoService::class.java)
        val call = produtoService.listar(offset, limit, categoriaId)
        call.enqueue(object : Callback<LodjinhaResponse<MutableList<Produto>>> {
            override fun onResponse(call: Call<LodjinhaResponse<MutableList<Produto>>>, response: Response<LodjinhaResponse<MutableList<Produto>>>) {
                if (response.isSuccessful) {
                    mutableLiveDataListar.value = response.body()
                } else {
                    val gson = Gson()
                    val type = object : TypeToken<LodjinhaResponse<MutableList<Produto>>>() {}.type
                    var errorResponse: LodjinhaResponse<MutableList<Produto>>? = gson.fromJson(response.errorBody()!!.charStream(), type)
                    mutableLiveDataListar.value = errorResponse
                }
            }

            override fun onFailure(call: Call<LodjinhaResponse<MutableList<Produto>>>, t: Throwable) {
                val lodjinhaResponse: LodjinhaResponse<MutableList<Produto>> = LodjinhaResponse(
                    null, null, null, null, t.message
                )
                mutableLiveDataListar.value = lodjinhaResponse
            }
        })
    }

    fun listarMaisVendidos() {
        val produtoService = ServiceGenerator.createService(ProdutoService::class.java)
        val call = produtoService.listarMaisVendidos()
        call.enqueue(object : Callback<LodjinhaResponse<MutableList<Produto>>> {
            override fun onResponse(call: Call<LodjinhaResponse<MutableList<Produto>>>, response: Response<LodjinhaResponse<MutableList<Produto>>>) {
                if (response.isSuccessful) {
                    mutableLiveDataListarMaisVendidos.value = response.body()
                } else {
                    val gson = Gson()
                    val type = object : TypeToken<LodjinhaResponse<MutableList<Produto>>>() {}.type
                    var errorResponse: LodjinhaResponse<MutableList<Produto>>? = gson.fromJson(response.errorBody()!!.charStream(), type)
                    mutableLiveDataListarMaisVendidos.value = errorResponse
                }
            }

            override fun onFailure(call: Call<LodjinhaResponse<MutableList<Produto>>>, t: Throwable) {
                val lodjinhaResponse: LodjinhaResponse<MutableList<Produto>> = LodjinhaResponse(
                    null, null, null, null, t.message
                )
                mutableLiveDataListarMaisVendidos.value = lodjinhaResponse
            }
        })
    }

    fun postById(produtoId: Int) {
        val produtoService = ServiceGenerator.createService(ProdutoService::class.java)
        val call = produtoService.postById(produtoId)
        call.enqueue(object : Callback<LodjinhaResponse<Produto>> {
            override fun onResponse(call: Call<LodjinhaResponse<Produto>>, response: Response<LodjinhaResponse<Produto>>) {
                if (response.isSuccessful) {
                    mutableLiveDataPostById.value = response.body()
                } else {
                    val gson = Gson()
                    val type = object : TypeToken<LodjinhaResponse<Produto>>() {}.type
                    var errorResponse: LodjinhaResponse<Produto>? = gson.fromJson(response.errorBody()!!.charStream(), type)
                    mutableLiveDataPostById.value = errorResponse
                }
            }

            override fun onFailure(call: Call<LodjinhaResponse<Produto>>, t: Throwable) {
                val lodjinhaResponse: LodjinhaResponse<Produto> = LodjinhaResponse(
                    null, null, null, null, t.message
                )
                mutableLiveDataPostById.value = lodjinhaResponse
            }
        })
    }
}