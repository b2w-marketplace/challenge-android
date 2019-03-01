package br.com.andrecouto.alodjinha.ui.fragment.category

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import br.com.andrecouto.alodjinha.R
import br.com.andrecouto.alodjinha.databinding.FragmentCategoryBinding
import br.com.andrecouto.alodjinha.databinding.ItemViewProductsByCategoryBinding
import br.com.andrecouto.alodjinha.domain.model.lodjinha.Category
import br.com.andrecouto.alodjinha.domain.model.lodjinha.Product
import br.com.andrecouto.alodjinha.ui.activity.product.ProductDetailsActivity
import br.com.andrecouto.alodjinha.ui.base.BaseFragment
import br.com.andrecouto.alodjinha.ui.base.ViewModelScope
import br.com.andrecouto.alodjinha.ui.base.adapter.SingleLayoutAdapter
import br.com.andrecouto.alodjinha.util.ConstantUtil
import br.com.andrecouto.alodjinha.util.extension.observe
import kotlinx.android.synthetic.main.fragment_category.*
import kotlinx.android.synthetic.main.fragment_product_details.*

class CategoryFragment : BaseFragment<CategoryViewModel, FragmentCategoryBinding>() {

    override val viewModel: CategoryViewModel by getLazyViewModel(ViewModelScope.ACTIVITY)
    override val layoutId: Int = R.layout.fragment_category

    override fun onViewInitialized(binding: FragmentCategoryBinding) {
        super.onViewInitialized(binding)

        binding.vm = viewModel

        binding.productAdapter = SingleLayoutAdapter<Product, ItemViewProductsByCategoryBinding>(
                R.layout.item_view_products_by_category,
                emptyList(),
                viewModel
        )

        activity?.getIntent()?.getExtras()?.getSerializable(ConstantUtil.CATEGORY)?.let {
            viewModel.getProducts( (it as Category).id)
            binding.item = it
        }

        observe(viewModel.products) {
            binding.productAdapter?.swapItems(it)
        }

        observe(viewModel.selectedProduct) {
            if (it.id > 0) openProductDetails(it)
        }

        (activity as? AppCompatActivity)?.setSupportActionBar(toolbarCategory)

        toolbarCategory.setNavigationOnClickListener {
            activity?.onBackPressed()
        }

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        //Product RecylerView
        val layoutManagerProduct = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerViewProducts.layoutManager = layoutManagerProduct
        recyclerViewProducts.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
    }

    private fun openProductDetails(product : Product) {
        val intent = Intent(activity!!, ProductDetailsActivity::class.java)
        intent.putExtra(ConstantUtil.PRODUCT, product)
        activity?.startActivity(intent)
    }

    companion object {
        val TAG: String = CategoryFragment::class.java.simpleName
        fun newInstance() = CategoryFragment()
    }

}
