package br.com.prodigosorc.lodjinha.retrofit.services.sinc

import android.util.Log
import br.com.prodigosorc.lodjinha.retrofit.RetrofitInicializador
import br.com.prodigosorc.lodjinha.retrofit.services.dto.ProdutoSync
import br.com.prodigosorc.lodjinha.retrofit.services.event.AtualizaListaProdutoEvent
import br.com.prodigosorc.lodjinha.retrofit.services.event.FailureRetrofitEvent
import br.com.prodigosorc.lodjinha.retrofit.services.event.ReservarProdutoEvent
import br.com.prodigosorc.lodjinha.util.Constants.Companion.ERRO_AO_CARREGAR_PRODUTOS
import br.com.prodigosorc.lodjinha.util.Constants.Companion.ERRO_AO_RESERVAR_PRODUTO
import de.greenrobot.event.EventBus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProdutoSincronizador {
    private val eventBus = EventBus.getDefault()

    fun buscaProdutosMaisVendidos() {
        val call = RetrofitInicializador().getProdutoService().listaMaiVendidos()
        call.enqueue(buscaProdutosCallback())
    }

    fun buscaProdutos() {
        val call = RetrofitInicializador().getProdutoService().listaProdutos()
        call.enqueue(buscaProdutosCallback())
    }

    fun reservarProduto(produtoId: Int) {
        val call = RetrofitInicializador().getProdutoService().reservarProduto(produtoId)
        call.enqueue(reservar())
    }

    private fun buscaProdutosCallback(): Callback<ProdutoSync> {
        return object : Callback<ProdutoSync> {
            override fun onResponse(call: Call<ProdutoSync>, response: Response<ProdutoSync>) {
                val produtoSync = response.body()

                Log.i("ProdutoSincronizador", "onResponse: ${produtoSync?.getData().toString()}")
                eventBus.post(AtualizaListaProdutoEvent(produtoSync?.getData()))
            }

            override fun onFailure(call: Call<ProdutoSync>, t: Throwable) {
                Log.e("ProdutoSincronizador", "onFailure sincroniza: ${t.message}")
                eventBus.post(FailureRetrofitEvent(ERRO_AO_CARREGAR_PRODUTOS))
            }
        }
    }

    private fun reservar(): Callback<Void> {
        return object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {

                Log.i("ProdutoSincronizador", "onResponse: ${response}")
                Log.i("ProdutoSincronizador", "onResponse: ${response.message()}")
                eventBus.post(ReservarProdutoEvent(response.message()))
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("ProdutoSincronizador", "onFailure sincroniza: ${t.message}")
                eventBus.post(FailureRetrofitEvent(ERRO_AO_RESERVAR_PRODUTO))
            }
        }
    }
}