package br.com.prodigosorc.lodjinha.retrofit.services.sinc

import android.util.Log
import br.com.prodigosorc.lodjinha.retrofit.RetrofitInicializador
import br.com.prodigosorc.lodjinha.retrofit.services.dto.BannerSync
import br.com.prodigosorc.lodjinha.retrofit.services.event.AtualizaListaBannerEvent
import br.com.prodigosorc.lodjinha.retrofit.services.event.FailureRetrofitEvent
import br.com.prodigosorc.lodjinha.util.Constants.Companion.ERRO_AO_CARREGAR_CATEGORIAS
import de.greenrobot.event.EventBus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BannerSincronizador {

    private val eventBus = EventBus.getDefault()

    fun carregaBanner() {
        val call = RetrofitInicializador().getBannerService().carregaBanner()
        call.enqueue(buscaCategoriaCallback())
    }

    private fun buscaCategoriaCallback(): Callback<BannerSync> {
        return object : Callback<BannerSync> {
            override fun onResponse(call: Call<BannerSync>, response: Response<BannerSync>) {
                val bannerSync = response.body()

                Log.i("CategoriaSincronizador", "onResponse: ${bannerSync?.getData().toString()}")
                eventBus.post(AtualizaListaBannerEvent(bannerSync?.getData()))
            }

            override fun onFailure(call: Call<BannerSync>, t: Throwable) {
                Log.e("CategoriaSincronizador", "onFailure sincroniza: ${t.message}")
                eventBus.post(FailureRetrofitEvent(ERRO_AO_CARREGAR_CATEGORIAS))
            }
        }
    }
}