package br.com.andrecouto.alodjinha.ui.fragment.product

import br.com.andrecouto.alodjinha.R
import br.com.andrecouto.alodjinha.databinding.FragmentProductDetailsBinding
import br.com.andrecouto.alodjinha.domain.model.lodjinha.Product
import br.com.andrecouto.alodjinha.ui.base.BaseFragment
import br.com.andrecouto.alodjinha.ui.base.ViewModelScope
import br.com.andrecouto.alodjinha.util.AlertUtil
import br.com.andrecouto.alodjinha.util.ConstantUtil
import br.com.andrecouto.alodjinha.util.extension.observe

class ProductDetailsFragment : BaseFragment<ProductDetailsViewModel, FragmentProductDetailsBinding>() {

    override val viewModel: ProductDetailsViewModel by getLazyViewModel(ViewModelScope.ACTIVITY)
    override val layoutId: Int = R.layout.fragment_product_details

    override fun onViewInitialized(binding: FragmentProductDetailsBinding) {
        super.onViewInitialized(binding)
        binding.vm = viewModel

        activity?.getIntent()?.getExtras()?.getSerializable(ConstantUtil.PRODUCT)?.let {
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


    }

    companion object {
        val TAG: String = ProductDetailsFragment::class.java.simpleName
        fun newInstance() = ProductDetailsFragment()
    }
}
