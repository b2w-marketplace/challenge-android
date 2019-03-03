package br.com.andrecouto.alodjinha.ui.fragment.home

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import br.com.andrecouto.alodjinha.databinding.FragmentHomeBinding
import br.com.andrecouto.alodjinha.ui.base.BaseFragment
import br.com.andrecouto.alodjinha.ui.base.ViewModelScope
import br.com.andrecouto.alodjinha.util.extension.observe
import br.com.andrecouto.alodjinha.databinding.ItemViewCategoryBinding
import br.com.andrecouto.alodjinha.domain.model.lodjinha.Banner
import br.com.andrecouto.alodjinha.domain.model.lodjinha.Category
import br.com.andrecouto.alodjinha.ui.base.adapter.SingleLayoutAdapter
import br.com.andrecouto.alodjinha.ui.fragment.home.adapter.BannerPagerAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import android.support.v7.widget.DividerItemDecoration
import br.com.andrecouto.alodjinha.R
import br.com.andrecouto.alodjinha.databinding.ItemViewProductsBinding
import br.com.andrecouto.alodjinha.domain.model.common.Status
import br.com.andrecouto.alodjinha.domain.model.lodjinha.Product
import br.com.andrecouto.alodjinha.ui.activity.category.CategoryActivity
import br.com.andrecouto.alodjinha.ui.activity.product.ProductDetailsActivity
import br.com.andrecouto.alodjinha.util.ConstantUtil

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    override val viewModel: HomeViewModel by getLazyViewModel(ViewModelScope.ACTIVITY)
    override val layoutId: Int = R.layout.fragment_home

    override fun onViewInitialized(binding: FragmentHomeBinding) {
        super.onViewInitialized(binding)

        binding.vm = viewModel
        binding.bannerAdapter = BannerPagerAdapter(viewModel)
        binding.categoryAdapter = SingleLayoutAdapter<Category, ItemViewCategoryBinding>(
                R.layout.item_view_category,
                emptyList(),
                viewModel
        )
        binding.productAdapter = SingleLayoutAdapter<Product, ItemViewProductsBinding>(
                R.layout.item_view_products,
                emptyList(),
                viewModel
        )

        observe(viewModel.banners) {
                resetBannerAdapter(it)
        }

        observe(viewModel.categories) {
            binding.categoryAdapter?.swapItems(it)
        }

        observe(viewModel.products) {
            binding.productAdapter?.swapItems(it)
        }

        observe(viewModel.selectedProduct) {
            if (it.id > 0) {
                openProductDetails(it)
            }
        }

        observe(viewModel.selectedCategory) {
            if (it.id > 0) {
              openProductListByCategory(it)
            }
        }

        observe(viewModel.statusBannner) {
            it?.let {
                when(it) {
                    Status.LOADED -> {
                        binding.bannerLoading = true
                        binding.errorMsg = false
                        binding.layout = false
                        binding.connection = false
                    }
                    Status.FAILED -> {
                        binding.bannerLoading = true
                        binding.categoryLoading = true
                        binding.productsLoading = true
                        binding.errorMsg = true
                        binding.layout = true
                    }
                    Status.NO_CONNECTION -> {
                        binding.bannerLoading = true
                        binding.categoryLoading = true
                        binding.productsLoading = true
                        binding.connection = true
                        binding.layout = true
                    }
                    else -> {}

                }
            }

        }

        observe(viewModel.statusCategory) {
            it?.let {
                when(it) {
                    Status.LOADED -> {
                        binding.categoryLoading = true
                        binding.errorMsg = false
                        binding.layout = false
                        binding.connection = false
                    }
                    Status.FAILED -> {
                        binding.bannerLoading = true
                        binding.categoryLoading = true
                        binding.productsLoading = true
                        binding.errorMsg = true
                        binding.layout = true
                    }
                    Status.NO_CONNECTION -> {
                        binding.bannerLoading = true
                        binding.categoryLoading = true
                        binding.productsLoading = true
                        binding.connection = true
                        binding.layout = true
                    }
                    else -> {}
                }
            }

        }

        observe(viewModel.statusProducts) {
            it?.let {
                when(it) {
                    Status.LOADED -> {
                        binding.productsLoading = true
                        binding.errorMsg = false
                        binding.layout = false
                        binding.connection = false
                    }
                    Status.FAILED -> {
                        binding.bannerLoading = true
                        binding.categoryLoading = true
                        binding.productsLoading = true
                        binding.errorMsg = true
                        binding.layout = true
                    }
                    Status.NO_CONNECTION -> {
                        binding.bannerLoading = true
                        binding.categoryLoading = true
                        binding.productsLoading = true
                        binding.connection = true
                        binding.layout = true
                    }
                    else -> {}
                }
            }

        }

        setupRecyclerViews()
    }

    override fun onResume() {
        super.onResume()
        viewModel.init()
    }

    private fun setupRecyclerViews() {
        // Category RecyclerView
        val layoutManagerCategory = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerCategories.layoutManager = layoutManagerCategory
        recyclerCategories.hasFixedSize()

        //Product RecylerView
        val layoutManagerProduct = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerProducts.layoutManager = layoutManagerProduct
        recyclerProducts.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
    }

    private fun resetBannerAdapter(bannerList : List<Banner>) {
        if (!bannerList.isNullOrEmpty()) {
            val adapter = BannerPagerAdapter(viewModel)
            adapter.items.clear()
            adapter.items.addAll(bannerList)
            adapter.notifyDataSetChanged()
            binding.bannerAdapter = adapter
        }
    }

    private fun openProductListByCategory(category : Category) {
        val intent = Intent(activity!!, CategoryActivity::class.java)
        intent.putExtra(ConstantUtil.CATEGORY, category)
        activity?.startActivity(intent)
    }

    private fun openProductDetails(product : Product) {
        val intent = Intent(activity!!, ProductDetailsActivity::class.java)
        intent.putExtra(ConstantUtil.PRODUCT, product)
        activity?.startActivity(intent)
    }

    companion object {
        val TAG: String = HomeFragment::class.java.simpleName
        fun newInstance() = HomeFragment()
    }

}
