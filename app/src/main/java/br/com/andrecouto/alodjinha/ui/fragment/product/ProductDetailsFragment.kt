package br.com.andrecouto.alodjinha.ui.fragment.product

import android.graphics.PorterDuff
import android.support.v7.app.AppCompatActivity
import br.com.andrecouto.alodjinha.R
import br.com.andrecouto.alodjinha.databinding.FragmentProductDetailsBinding
import br.com.andrecouto.alodjinha.domain.model.common.Status
import br.com.andrecouto.alodjinha.domain.model.lodjinha.Product
import br.com.andrecouto.alodjinha.ui.base.BaseFragment
import br.com.andrecouto.alodjinha.ui.base.ViewModelScope
import br.com.andrecouto.alodjinha.ui.view.CustomAppBarLayout
import br.com.andrecouto.alodjinha.util.AlertUtil
import br.com.andrecouto.alodjinha.util.ConstantUtil
import br.com.andrecouto.alodjinha.util.extension.observe
import kotlinx.android.synthetic.main.fragment_product_details.*

class ProductDetailsFragment : BaseFragment<ProductDetailsViewModel, FragmentProductDetailsBinding>() {

    override val viewModel: ProductDetailsViewModel by getLazyViewModel(ViewModelScope.ACTIVITY)
    override val layoutId: Int = R.layout.fragment_product_details

    override fun onViewInitialized(binding: FragmentProductDetailsBinding) {
        super.onViewInitialized(binding)
        binding.vm = viewModel

        activity?.intent?.extras?.getSerializable(ConstantUtil.PRODUCT)?.let {
            viewModel.getProducts( (it as Product).id)
        }

        observe(viewModel.product) {
            if(it.id > 0) binding.item = it
        }

        observe(viewModel.resultRetainProduct) {
            if (it.isNotEmpty()) {
                AlertUtil.alertDialogShow(activity!!, resources.getString(R.string.reatin_product_phrase))
            }
        }

        observe(viewModel.status) {
            when(it) {
                Status.LOADING -> {
                    binding.loading = false
                    binding.layout = true
                }
                Status.LOADED -> {
                    binding.loading = true
                    binding.layout = false
                }
                Status.NO_CONNECTION -> {
                    binding.layout = true
                    binding.loading = true
                    binding.connection = true
                }
                Status.FAILED -> {
                    binding.layout = true
                    binding.loading = true
                    binding.errorMsg = true
                }
                else -> {}
            }
        }

        init()
    }

    fun init() {

        (activity as? AppCompatActivity)?.setSupportActionBar(toolbar)
        collapsing_toolbar.setBackgroundColor(activity?.resources?.getColor(R.color.white)!!)

        toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }

        appbar.setOnStateChangeListener(object : CustomAppBarLayout.OnStateChangeListener {
            override fun onStateChange(toolbarChange: CustomAppBarLayout.State) {
                when (toolbarChange) {
                    CustomAppBarLayout.State.COLLAPSED -> {
                        binding.collapse = true
                        toolbar.setBackgroundColor(activity?.resources?.getColor(R.color.colorPrimary)!!)
                        toolbar.navigationIcon?.setColorFilter(resources.getColor(R.color.white), PorterDuff.Mode.SRC_ATOP)
                    }
                    else -> {
                        binding.collapse = false
                        toolbar.setBackgroundColor(activity?.resources?.getColor(R.color.transparent)!!)
                        toolbar.navigationIcon?.setColorFilter(resources.getColor(R.color.black_2d3142), PorterDuff.Mode.SRC_ATOP)
                    }
                }
            }
        })
    }

    companion object {
        val TAG: String = ProductDetailsFragment::class.java.simpleName
        fun newInstance() = ProductDetailsFragment()
    }
}
