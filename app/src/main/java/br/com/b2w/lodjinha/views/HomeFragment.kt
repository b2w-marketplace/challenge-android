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
        categoryView.onCategorySelected { category ->
            val action = HomeFragmentDirections.actionHomeFragmentToProductsFragment(
                category.description,
                category
            )
            findNavController().navigate(action)
        }

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
        bestSellerProductsView.onProductSelected { product ->
            val action = HomeFragmentDirections.actionHomeFragmentToProductDetailsFragment(
                product.category.description,
                product.id
            )
            findNavController().navigate(action)
        }

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