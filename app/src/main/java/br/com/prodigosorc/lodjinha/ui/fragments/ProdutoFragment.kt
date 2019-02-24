package br.com.prodigosorc.lodjinha.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.prodigosorc.lodjinha.R
import br.com.prodigosorc.lodjinha.models.Produto
import br.com.prodigosorc.lodjinha.retrofit.services.event.AtualizaListaProdutoEvent
import br.com.prodigosorc.lodjinha.retrofit.services.event.FailureRetrofitEvent
import br.com.prodigosorc.lodjinha.retrofit.services.sinc.ProdutoSincronizador
import br.com.prodigosorc.lodjinha.ui.activities.ProdutoActivity
import br.com.prodigosorc.lodjinha.ui.dialogs.CreateDialog
import br.com.prodigosorc.lodjinha.ui.adapter.ListaProdutoAdapter
import br.com.prodigosorc.lodjinha.ui.adapter.listener.OnItemProdutoClickListener
import br.com.prodigosorc.lodjinha.util.Constants
import br.com.prodigosorc.lodjinha.util.Constants.Companion.SEM_PRODUTOS_DISPONIVEIS
import de.greenrobot.event.EventBus
import de.greenrobot.event.Subscribe
import de.greenrobot.event.ThreadMode
import kotlinx.android.synthetic.main.fragment_produto.*
import kotlinx.android.synthetic.main.fragment_produto.view.*

class ProdutoFragment : Fragment() {

    private val produtoSincronizador = ProdutoSincronizador()
    private val eventBus = EventBus.getDefault()
    private var swipe: SwipeRefreshLayout? = null
    private lateinit var descricaoCategoria: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_produto, container, false)
        carregaBundle()
        inicializaLista(view)

        return view
    }

    private fun carregaBundle() {
        val parametros = arguments
        if (parametros != null) {
            descricaoCategoria = parametros.getSerializable(Constants.DESCRICAO_CATEGORIA) as String
            (activity as ProdutoActivity).setActionBarTitle(descricaoCategoria)
        }
    }

    private fun inicializaLista(view: View) {
        swipe = view.swipe_lista_produto
        view.swipe_lista_produto.setOnRefreshListener { produtoSincronizador.buscaProdutos() }
        produtoSincronizador.buscaProdutos()
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    fun atualizaListaProdutoEvent(produtoEvent: AtualizaListaProdutoEvent) {
        Log.i("ProdutoFragment", "AtualizaListaProdutoEvent: ${produtoEvent.produtoSync}")
        swipeDismiss()
        carregarProduto(produtoEvent.produtoSync)
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    fun failureRetrofit(failureRetrofitEvent: FailureRetrofitEvent) {
        Log.i("ProdutoFragment", "AtualizaListaProdutoEvent: $failureRetrofitEvent")
        swipeDismiss()
        CreateDialog().display(activity, failureRetrofitEvent.mensagem, true)
    }

    private fun carregarProduto(produtoList: List<Produto>?) {
        filtraProdutos(produtoList)
    }

    private fun filtraProdutos(
        produtoList: List<Produto>?
    ) {
        val filtraCategoria: MutableList<Produto> = mutableListOf()
        if (produtoList != null) {
            produtoList.forEach { produto ->
                if (produto.categoria.descricao == descricaoCategoria) {
                    filtraCategoria.add(produto)
                }
            }
            if (!filtraCategoria.isEmpty()) {
                criaAdapter(rv_produto, filtraCategoria)
            } else {
                CreateDialog().display(activity, SEM_PRODUTOS_DISPONIVEIS, true)
            }
        }
    }

    private fun criaAdapter(
        recyclerView: RecyclerView,
        filtraCategoria: List<Produto>
    ) {
        recyclerView.adapter = ListaProdutoAdapter(filtraCategoria, context)
        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
        (recyclerView.adapter as ListaProdutoAdapter).setOnItemClickListener(object :
            OnItemProdutoClickListener {
            override fun onItemClick(produto: Produto?) {
                vaiParaCategoriaDoProdutoActivity(produto)
            }
        })
    }

    private fun vaiParaCategoriaDoProdutoActivity(produto: Produto?) {
        val intent = Intent(context, ProdutoActivity::class.java)
        intent.putExtra(Constants.PRODUTO_DETALHE, produto)
        startActivity(intent)
    }

    private fun swipeDismiss() {
        if (swipe?.isRefreshing!!) swipe?.isRefreshing = false
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
