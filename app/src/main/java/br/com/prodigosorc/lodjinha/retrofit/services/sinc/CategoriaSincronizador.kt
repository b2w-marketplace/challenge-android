package br.com.prodigosorc.lodjinha.retrofit.services.sinc

import android.util.Log
import br.com.prodigosorc.lodjinha.retrofit.services.dto.CategoriaSync
import br.com.prodigosorc.lodjinha.retrofit.services.event.AtualizaListaCategoriaEvent
import br.com.prodigosorc.lodjinha.retrofit.RetrofitInicializador
import br.com.prodigosorc.lodjinha.retrofit.services.event.FailureRetrofitEvent
import br.com.prodigosorc.lodjinha.util.Constants.Companion.ERRO_AO_CARREGAR_CATEGORIAS
import de.greenrobot.event.EventBus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoriaSincronizador {

    private val eventBus = EventBus.getDefault()

    fun buscaCategorias() {
        val call = RetrofitInicializador().getCategoriaService().listaCategoria()
        call.enqueue(buscaCategoriaCallback())
    }

    private fun buscaCategoriaCallback(): Callback<CategoriaSync> {
        return object : Callback<CategoriaSync> {
            override fun onResponse(call: Call<CategoriaSync>, response: Response<CategoriaSync>) {
                val categoriaSync = response.body()

                Log.i("CategoriaSincronizador", "onResponse: ${categoriaSync?.getData().toString()}")
                eventBus.post(AtualizaListaCategoriaEvent(categoriaSync?.getData()))
            }

            override fun onFailure(call: Call<CategoriaSync>, t: Throwable) {
                Log.e("CategoriaSincronizador", "onFailure sincroniza: ${t.message}")
                eventBus.post(FailureRetrofitEvent(ERRO_AO_CARREGAR_CATEGORIAS))
            }
        }
    }
}