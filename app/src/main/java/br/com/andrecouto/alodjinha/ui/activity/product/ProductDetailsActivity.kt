package br.com.andrecouto.alodjinha.ui.activity.product

import android.os.Bundle
import br.com.andrecouto.alodjinha.R
import br.com.andrecouto.alodjinha.databinding.ActivityProductDetailsBinding
import br.com.andrecouto.alodjinha.ui.base.BaseActivity
import br.com.andrecouto.alodjinha.ui.fragment.product.ProductDetailsFragment
import br.com.andrecouto.alodjinha.ui.fragment.product.ProductDetailsViewModel
import br.com.andrecouto.alodjinha.util.FragmentUtil
import kotlinx.android.synthetic.main.fragment_product_details.*

class ProductDetailsActivity : BaseActivity<ProductDetailsViewModel, ActivityProductDetailsBinding>() {

    override val layoutId: Int = R.layout.activity_product_details
    override val viewModel: ProductDetailsViewModel by getLazyViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        FragmentUtil.replaceFragment(supportFragmentManager, ProductDetailsFragment.newInstance(), R.id.frame_productdetails_container, false)

    }
}
