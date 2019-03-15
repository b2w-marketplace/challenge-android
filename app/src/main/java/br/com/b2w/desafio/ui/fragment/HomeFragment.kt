package br.com.b2w.desafio.ui.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.b2w.desafio.R
import br.com.b2w.desafio.databinding.BannerViewBinding
import br.com.b2w.desafio.databinding.FragmentHomeBinding
import br.com.b2w.desafio.model.Banner
import br.com.b2w.desafio.model.Categoria
import br.com.b2w.desafio.model.produto.Produto
import br.com.b2w.desafio.model.response.LodjinhaResponse
import br.com.b2w.desafio.ui.activity.DetalheProdutoActivity
import br.com.b2w.desafio.ui.activity.ProdutoPorCategoriaActivity
import br.com.b2w.desafio.ui.adapter.BannerPageAdapter
import br.com.b2w.desafio.ui.adapter.ProdutoAdapter
import br.com.b2w.desafio.ui.viewmodel.BannerViewModel
import br.com.b2w.desafio.ui.viewmodel.CategoriaViewModel
import br.com.b2w.desafio.ui.viewmodel.ProdutoViewModel
import br.com.b2w.desafio.util.Alert
import br.gov.sp.detran.consultas.adapter.CategoriaAdapter
import com.bumptech.glide.Glide

class HomeFragment : Fragment(), ProdutoAdapter.AgendaAdapterOnClickListener, CategoriaAdapter.AgendaAdapterOnClickListener {

    private lateinit var binding: FragmentHomeBinding

    private val bannerViewModel: BannerViewModel by lazy {
        ViewModelProviders.of(this).get(BannerViewModel::class.java)
    }

    private val produtoViewModel: ProdutoViewModel by lazy {
        ViewModelProviders.of(this).get(ProdutoViewModel::class.java)
    }

    private val categoriaViewModel: CategoriaViewModel by lazy {
        ViewModelProviders.of(this).get(CategoriaViewModel::class.java)
    }

    private lateinit var produtoAdapter: ProdutoAdapter
    private lateinit var categoriaAdapter: CategoriaAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        retainInstance
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        addObservable()
        listarBanners()
        listarCategorias()
        listarMaisVendidos()

        return binding.root
    }

    private fun listarBanners(){
        bannerViewModel.listar()
    }

    private fun listarCategorias(){
        categoriaViewModel.listar()
    }

    private fun listarMaisVendidos(){
        produtoViewModel.listarMaisVendidos()
    }

    private fun addObservable(){
        bannerViewModel.listarObservable().observe(this, Observer<LodjinhaResponse<MutableList<Banner>>> { bannerResponse ->

            if(bannerResponse != null){
                if(bannerResponse.message.isNullOrEmpty()){
                    val bannerAdapter = BannerPageAdapter()
                    bannerAdapter.data = createPageList(bannerResponse.data!!)

                    binding.pager.adapter = bannerAdapter
                    binding.viewPagerIndicator.setupWithViewPager(binding.pager)
                    binding.viewPagerIndicator.addOnPageChangeListener(mOnPageChangeListener)
                } else {
                    Alert.showDialogSimples(getString(R.string.msg_erro_banner), context!!)
                }
            } else {
                Alert.showDialogSimples(getString(R.string.msg_erro_banner), context!!)
            }
        })

        categoriaViewModel.listarObservable().observe(this, Observer<LodjinhaResponse<MutableList<Categoria>>> { categoriaResponse ->

            if(categoriaResponse != null){
                if(categoriaResponse.message.isNullOrEmpty()){
                    setAdapterCategoria(categoriaResponse.data!!)
                } else {
                    Alert.showDialogSimples(getString(R.string.msg_erro_categoria), context!!)
                }
            } else {
                Alert.showDialogSimples(getString(R.string.msg_erro_categoria), context!!)
            }
        })

        produtoViewModel.listarMaisVendidosObservable().observe(this, Observer<LodjinhaResponse<MutableList<Produto>>> { produtoResponse ->

            if(produtoResponse != null){
                if(produtoResponse.message.isNullOrEmpty()){
                    setAdapterProdutosMaisVendidos(produtoResponse.data!!)
                } else {
                    Alert.showDialogSimples(getString(R.string.msg_erro_produtos_mais_vendidos), context!!)
                }
            } else {
                Alert.showDialogSimples(getString(R.string.msg_erro_produtos_mais_vendidos), context!!)
            }
        })
    }

    private fun setAdapterCategoria(categorias: MutableList<Categoria>){
        binding.recyclerCategorias.setHasFixedSize(true)
        binding.recyclerCategorias.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        categoriaAdapter = CategoriaAdapter(context!!, this)
        categoriaAdapter.setData(categorias)
        binding.recyclerCategorias.adapter = categoriaAdapter
    }

    private fun setAdapterProdutosMaisVendidos(produtos: MutableList<Produto>){
        binding.recyclerMaisVendidos.setHasFixedSize(true)
        binding.recyclerMaisVendidos.layoutManager = LinearLayoutManager(context)
        produtoAdapter = ProdutoAdapter(context!!, this)
        produtoAdapter.setData(produtos)
        binding.recyclerMaisVendidos.adapter = produtoAdapter
    }

    override fun onClickProduto(produto: Produto?) {
        val intent = Intent(context, DetalheProdutoActivity::class.java)
        intent.putExtra(getString(R.string.param_produto), produto)
        startActivity(intent)
    }

    override fun onClickCategoria(categoria: Categoria?) {
        val intent = Intent(context, ProdutoPorCategoriaActivity::class.java)
        intent.putExtra(getString(R.string.param_categoria), categoria)
        startActivity(intent)
    }

    private fun createPageList(banners: List<Banner>): List<View> {
        val pageList = ArrayList<View>()
        for(item in banners){
            pageList.add(createPageView(item))
        }

        return pageList
    }

    private fun createPageView(banner: Banner): View {
        val inflater = LayoutInflater.from(context)
        val binding = BannerViewBinding.inflate(inflater, null, false)

        binding.ivBanner.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(banner.linkUrl)))
        }

        Glide
            .with(context!!)
            .load(banner.urlImagem)
            .centerCrop()
            .placeholder(R.drawable.loading)
            .error(R.mipmap.ic_nao_disponivel)
            .into(binding.ivBanner)

        return binding.root
    }

    private val mOnPageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
        }

        override fun onPageSelected(position: Int) {
        }

        override fun onPageScrollStateChanged(state: Int) {
        }
    }
}