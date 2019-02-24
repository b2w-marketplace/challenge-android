package br.com.prodigosorc.lodjinha.ui.fragments

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.prodigosorc.lodjinha.R
import br.com.prodigosorc.lodjinha.models.Banner
import br.com.prodigosorc.lodjinha.models.Categoria
import br.com.prodigosorc.lodjinha.models.Produto
import br.com.prodigosorc.lodjinha.retrofit.services.event.AtualizaListaBannerEvent
import br.com.prodigosorc.lodjinha.retrofit.services.event.AtualizaListaCategoriaEvent
import br.com.prodigosorc.lodjinha.retrofit.services.event.AtualizaListaProdutoEvent
import br.com.prodigosorc.lodjinha.retrofit.services.event.FailureRetrofitEvent
import br.com.prodigosorc.lodjinha.retrofit.services.sinc.BannerSincronizador
import br.com.prodigosorc.lodjinha.retrofit.services.sinc.CategoriaSincronizador
import br.com.prodigosorc.lodjinha.retrofit.services.sinc.ProdutoSincronizador
import br.com.prodigosorc.lodjinha.ui.activities.ProdutoActivity
import br.com.prodigosorc.lodjinha.ui.adapter.BannerAdapter
import br.com.prodigosorc.lodjinha.ui.dialogs.CreateDialog
import br.com.prodigosorc.lodjinha.ui.adapter.ListaCategoriaAdapter
import br.com.prodigosorc.lodjinha.ui.adapter.ListaProdutoAdapter
import br.com.prodigosorc.lodjinha.ui.adapter.listener.OnItemBannerClickListener
import br.com.prodigosorc.lodjinha.ui.adapter.listener.OnItemCategoriaClickListener
import br.com.prodigosorc.lodjinha.ui.adapter.listener.OnItemProdutoClickListener
import br.com.prodigosorc.lodjinha.util.Constants.Companion.DESCRICAO_CATEGORIA
import br.com.prodigosorc.lodjinha.util.Constants.Companion.PRODUTO_DETALHE
import de.greenrobot.event.EventBus
import de.greenrobot.event.Subscribe
import de.greenrobot.event.ThreadMode
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import android.net.Uri

class MainFragment : Fragment() {

    private val bannerSincronizador = BannerSincronizador()
    private val categoriaSincronizador = CategoriaSincronizador()
    private val produtoSincronizador = ProdutoSincronizador()
    private val eventBus = EventBus.getDefault()
    private var swipe: SwipeRefreshLayout? = null
    private val mVpBanner by lazy { vp_banner }
    private val mRvCategorias by lazy { rv_categorias }
    private val mRvMaisVendidos by lazy { rv_mais_vendidos }
    private val mTabIndicator by lazy { tab_indicator }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        bannerSincronizador.carregaBanner()

        categoriaSincronizador.buscaCategorias()
        inicializaItensMaisVendidos(view)

        return view
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    fun atualizaListaBannerEvent(atualizaListaBannerEvent: AtualizaListaBannerEvent) {
        Log.i("MainFragment", "atualizaListaCategoriaEvent: ${atualizaListaBannerEvent.bannerSync}")
        atualizaBanner(atualizaListaBannerEvent.bannerSync)
    }

    private fun atualizaBanner(bannerList: List<Banner>?) {
        mVpBanner.adapter = BannerAdapter(bannerList, context)
        mTabIndicator.setupWithViewPager(mVpBanner, true)
        (mVpBanner.adapter as BannerAdapter).setOnItemClickListener(object : OnItemBannerClickListener{
            override fun onItemClick(linkUrl: String?) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(linkUrl)))
            }
        })
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    fun atualizaListaCategoriaEvent(categoriaEvent: AtualizaListaCategoriaEvent) {
        Log.i("MainFragment", "atualizaListaCategoriaEvent: ${categoriaEvent.categoriaSync}")
        carregarCategoria(categoriaEvent.categoriaSync)
    }

    private fun carregarCategoria(categoriaList: List<Categoria>?) {
        mRvCategorias.adapter = ListaCategoriaAdapter(categoriaList, context)
        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
        mRvCategorias.layoutManager = layoutManager
        (mRvCategorias.adapter as ListaCategoriaAdapter).setOnItemClickListener(object : OnItemCategoriaClickListener {
            override fun onItemClick(descricaoCategoria: String?) {
                vaiParaCategoriaDoProdutoActivity(descricaoCategoria)
            }
        })
    }

    private fun vaiParaCategoriaDoProdutoActivity(descricaoCategoria: String?) {
        val intent = Intent(context, ProdutoActivity::class.java)
        intent.putExtra(DESCRICAO_CATEGORIA, descricaoCategoria)
        startActivity(intent)
    }

    private fun inicializaItensMaisVendidos(view: View) {
        swipe = view.swipe_lista_mais_vendidos
        view.swipe_lista_mais_vendidos.setOnRefreshListener { produtoSincronizador.buscaProdutosMaisVendidos() }
        produtoSincronizador.buscaProdutosMaisVendidos()
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    fun atualizaListaProdutoEvent(produtoEvent: AtualizaListaProdutoEvent) {
        Log.i("MainFragment", "AtualizaListaProdutoEvent: ${produtoEvent.produtoSync}")
        swipeDismiss()
        carregarProduto(produtoEvent.produtoSync)
    }

    private fun carregarProduto(produtoList: List<Produto>?) {
        mRvMaisVendidos.adapter = ListaProdutoAdapter(produtoList, context)
        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        mRvMaisVendidos.layoutManager = layoutManager
        (mRvMaisVendidos.adapter as ListaProdutoAdapter).setOnItemClickListener(object : OnItemProdutoClickListener {
            override fun onItemClick(produto: Produto?) {
                vaiParaCategoriaDoProdutoActivity(produto)
            }
        })
    }

    private fun vaiParaCategoriaDoProdutoActivity(produto: Produto?) {
        val intent = Intent(context, ProdutoActivity::class.java)
        intent.putExtra(PRODUTO_DETALHE, produto)
        startActivity(intent)
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    fun failureRetrofit(failureRetrofitEvent: FailureRetrofitEvent) {
        Log.i("MainFragment", "failureRetrofit: $failureRetrofitEvent")
        swipeDismiss()
        CreateDialog().display(activity, failureRetrofitEvent.mensagem, false)
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
