package br.com.prodigosorc.lodjinha.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.com.prodigosorc.lodjinha.R
import br.com.prodigosorc.lodjinha.models.Produto
import br.com.prodigosorc.lodjinha.retrofit.services.event.FailureRetrofitEvent
import br.com.prodigosorc.lodjinha.retrofit.services.event.ReservarProdutoEvent
import br.com.prodigosorc.lodjinha.retrofit.services.sinc.ProdutoSincronizador
import br.com.prodigosorc.lodjinha.ui.activities.ProdutoActivity
import br.com.prodigosorc.lodjinha.ui.dialogs.CreateDialog
import br.com.prodigosorc.lodjinha.util.Constants.Companion.PREFIXO_DE
import br.com.prodigosorc.lodjinha.util.Constants.Companion.PREFIXO_POR
import br.com.prodigosorc.lodjinha.util.Constants.Companion.PRODUTO_DETALHE
import br.com.prodigosorc.lodjinha.util.Constants.Companion.PRODUTO_RESERVADO_COM_SUCESSO
import br.com.prodigosorc.lodjinha.util.Constants.Companion.TITULO_DETALHE
import br.com.prodigosorc.lodjinha.util.extensions.limitarTitulos
import br.com.prodigosorc.lodjinha.util.extensions.formataParaBrasileiro
import br.com.prodigosorc.lodjinha.util.extensions.strike
import com.squareup.picasso.Picasso
import de.greenrobot.event.EventBus
import de.greenrobot.event.Subscribe
import de.greenrobot.event.ThreadMode
import kotlinx.android.synthetic.main.fragment_produto_detalhe.view.*

class ProdutoDetalheFragment : Fragment() {

    private val produtoSincronizador = ProdutoSincronizador()
    private val eventBus = EventBus.getDefault()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_produto_detalhe, container, false)
        (activity as ProdutoActivity).setActionBarTitle(TITULO_DETALHE)
        val parametros = arguments
        if (parametros != null) {
            val produto = parametros.getParcelable(PRODUTO_DETALHE) as Produto
            Picasso.get()
                .load(produto.urlImagem)
                .error(android.R.drawable.ic_menu_close_clear_cancel)
                .into(view.iv_produto)
            view.tv_descricao_produto.text = limitarTitulos(produto.descricao, 80)
            view.tv_preco_de.text = produto.precoDe.formataParaBrasileiro(PREFIXO_DE)
            view.tv_preco_de.strike()
            view.tv_preco_por.text = produto.precoPor.formataParaBrasileiro(PREFIXO_POR)
            view.tv_titulo_detalhe.text = limitarTitulos(produto.descricao, 20)
            view.tv_detalhes.text = produto.descricao.replace("<br/>", "\n")
            view.btn_confirma_reserva.setOnClickListener {
                view.btn_confirma_reserva.isEnabled = false
                produtoSincronizador.reservarProduto(produto.id)
            }
        }

        return view
    }

    private fun chamaDialogDeAdicao() {
        CreateDialog()
            .display(activity, PRODUTO_RESERVADO_COM_SUCESSO, true)
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    fun reservarProduto(mensagem: ReservarProdutoEvent) {
        Log.i("ProdutoDetalheFragment", "reservarProduto: ${mensagem}")
        chamaDialogDeAdicao()
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    fun failureRetrofit(failureRetrofitEvent: FailureRetrofitEvent) {
        Log.i("ProdutoDetalheFragment", "failureRetrofit: $failureRetrofitEvent")
        view?.btn_confirma_reserva?.isEnabled = true
        CreateDialog().display(activity, failureRetrofitEvent.mensagem, false)
    }

    override fun onResume() {
        super.onResume()
        eventBus.register(this)
    }

    override fun onPause() {
        super.onPause()
        eventBus.unregister(this)
    }

}
