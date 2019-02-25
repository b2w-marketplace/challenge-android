package com.bryanollivie.lojinha.ui.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bryanollivie.lojinha.R
import com.bryanollivie.lojinha.data.model.BannerLoja
import com.bryanollivie.lojinha.data.model.Categoria
import com.bryanollivie.lojinha.data.model.Produto
import com.bryanollivie.lojinha.ui.base.BaseFragment
import com.bryanollivie.lojinha.ui.home.adapters.CategoriaAdapter
import com.bryanollivie.lojinha.ui.home.adapters.GlideImageLoader
import com.bryanollivie.lojinha.ui.home.adapters.ProdutoAdapter
import com.bryanollivie.lojinha.ui.produto.ProdutoListActivity
import com.bryanollivie.lojinha.ui.produto.detalhe.ProdutoDetalheActivity
import com.jam.utils.easybanner.EasyBannerConfig
import com.jam.utils.easybanner.listener.OnEasyBannerListener
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : BaseFragment<HomeContract.View, HomeContract.Presenter>(), HomeContract.View{


    var categoriaAdapter: CategoriaAdapter? = null
    var maisVendidosAdapter: ProdutoAdapter? = null

    override var presenter: HomeContract.Presenter = HomePresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.getBanners(activity!!)
        presenter.getCategorias(activity!!)
        presenter.getMaisVendidos(activity!!)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun showBanner(banners: List<Any>) {


        val urls = ArrayList<String>()

        for (banner in banners) {
            urls.add(BannerLoja.toObject(banner)["urlImagem"].toString())
        }
        home_banner
            .setData(urls as ArrayList<Any>)
            .isAutoPlay(true)
            .setIndicatorStyle(EasyBannerConfig.CIRCLE_INDICATOR)
            .setDisplayLoader(GlideImageLoader())
            .setOnEasyBannerListener(object : OnEasyBannerListener {
                override fun onBannerClick(position: Int) {
                    Toast.makeText(activity, position.toString(), Toast.LENGTH_SHORT).show()
                }
            })
            .start()
    }

    override fun showCategorias(categorias: List<Any>) {
        recyclerHomeCategoria.setHasFixedSize(true)
        recyclerHomeCategoria.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        categoriaAdapter = CategoriaAdapter(categorias) { itemClick ->
            val intent = Intent(activity, ProdutoListActivity::class.java)
            intent.putExtra("categoriaId", itemClick)
            startActivity(intent)
        }
        recyclerHomeCategoria.adapter = categoriaAdapter

    }

    override fun showMaisVendidos(maisVendidos: List<Any>) {
        recyclerHomeMaisVendidos.setHasFixedSize(true)
        recyclerHomeMaisVendidos.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        maisVendidosAdapter = ProdutoAdapter(maisVendidos) { itemClick ->
            val intent = Intent(activity!!, ProdutoDetalheActivity::class.java)
            intent.putExtra("produtoId", itemClick)
            startActivity(intent)
        }
        recyclerHomeMaisVendidos.adapter = maisVendidosAdapter

    }

}
