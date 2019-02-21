package com.bryanollivie.lojinha.ui.home

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bannerlayout.listener.OnBannerClickListener
import com.bryanollivie.lojinha.R
import com.bryanollivie.lojinha.data.model.BannerLoja
import com.bryanollivie.lojinha.data.model.Categoria
import com.bryanollivie.lojinha.ui.base.BaseFragment
import com.bryanollivie.lojinha.ui.home.adapters.CategoriaAdapter
import com.bryanollivie.lojinha.ui.home.adapters.ProdutoAdapter
import com.bryanollivie.lojinha.ui.produto.ProdutoListActivity
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : BaseFragment<HomeContract.View, HomeContract.Presenter>(), HomeContract.View,
    OnBannerClickListener<BannerLoja> {


    var categoriaAdapter: CategoriaAdapter? = null
    var maisVendidosAdapter: ProdutoAdapter? = null

    override var presenter: HomeContract.Presenter = HomePresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //presenter.getBanners(activity!!)
        presenter.getCategorias(activity!!)
        presenter.getMaisVendidos(activity!!)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun showBanner(banners: List<BannerLoja>) {
        /*if (banners != null) {
            if (banners.size != 0) {

                home_banner.apply {
                    delayTime = 2000
                    dotsSelector = R.drawable.banner_selector
                    pageNumViewBottomMargin = 12
                    pageNumViewLeftMargin = 12
                    pageNumViewRightMargin = 12
                    pageNumViewTopMargin = 12
                    imageLoaderManager = BannerAdapter()
                    onBannerClickListener = this@HomeFragment
                }
                    .initPageNumView()
                    .resource(banners as MutableList)
                    .switchBanner(true)

            }
        }*/
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
            //openBook(itemClick.sku.toString())
        }
        recyclerHomeMaisVendidos.adapter = maisVendidosAdapter

    }

    override fun onBannerClick(view: View, position: Int, model: BannerLoja) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
