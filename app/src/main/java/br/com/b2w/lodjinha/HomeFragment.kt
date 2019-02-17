package br.com.b2w.lodjinha

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.view_category.view.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getBanners()
        getCategories()
        getBestSellerProducts()
    }

    private fun getBanners() {
        launch {
            homeViewModel.getBannersUrl().observe(this@HomeFragment, Observer { bannersUrl ->
                bannerView.setImages(bannersUrl)
            })
        }
    }

    private fun getCategories() {
        launch {
            homeViewModel.getCategories().observe(this@HomeFragment, Observer { categories ->
                with(categoryView) {
                    setCategories(categories)
                    visibility = View.VISIBLE
                }
            })
        }
    }

    private fun getBestSellerProducts() {
        launch {
            homeViewModel.getBestSellerProducts().observe(this@HomeFragment, Observer { products ->
                with(bestSellerProductsView) {
                    setProducts(products)
                    visibility = View.VISIBLE
                }
            })
        }
    }
}