package br.com.andreguedes.alodjinha.ui.main.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.com.andreguedes.alodjinha.R
import br.com.andreguedes.alodjinha.data.model.Banner
import br.com.andreguedes.alodjinha.data.model.Category
import br.com.andreguedes.alodjinha.data.model.Product

/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment : Fragment(), HomeContract.View {

    override lateinit var presenter: HomeContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = HomePresenter(this)
        presenter.start()
    }

    override fun setBanners(banners: List<Banner>) {

    }

    override fun setCategories(categories: List<Category>) {

    }

    override fun setProductsBestSellers(productsBestSellers: List<Product>) {

    }

}