package br.com.b2w.lodjinha.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import br.com.b2w.lodjinha.R
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeHomeData()
        onCategorySelected()
        onProductSelected()
        (activity as MainActivity).showToolbarLogoInfo()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as MainActivity).hideToolbarLogoInfo()
    }

    private fun observeHomeData() {
        launch { homeViewModel.getHomeData() }
        homeViewModel.homeLiveData.observe(this, Observer { home ->
            homeProgressBar.hide()
            with(bannerView) {
                setImages(home.banners.map { banner -> banner.urlImagem })
            }
            with(categoryView) {
                setCategories(home.categories)
                visibility = View.VISIBLE
            }
            with(bestSellerProductsView) {
                setProducts(home.products)
                visibility = View.VISIBLE
            }
        })
    }

    private fun onCategorySelected() {
        categoryView.onCategorySelected { category ->
            val action = HomeFragmentDirections.actionHomeFragmentToProductsFragment(
                category.description,
                category
            )
            findNavController().navigate(action)
        }
    }

    private fun onProductSelected() {
        bestSellerProductsView.onProductSelected { product ->
            val action = HomeFragmentDirections.actionHomeFragmentToProductDetailsFragment(
                product.category.description,
                product.id
            )
            findNavController().navigate(action)
        }
    }
}