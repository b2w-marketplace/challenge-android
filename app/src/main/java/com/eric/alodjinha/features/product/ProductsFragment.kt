package com.eric.alodjinha.features.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.eric.alodjinha.MainActivity
import com.eric.alodjinha.R
import com.eric.alodjinha.base.Constants

class ProductsFragment : Fragment(), ProductsFragmentView {

    val presenter: ProductsFragmentPresenter = ProductsFragmentPresenterImpl(this)


    companion object {

        var homeFragment: ProductsFragment? = null

        fun getInstance(categoryId: Int): ProductsFragment {

            if (homeFragment == null) {

                val extras = Bundle()
                extras.putInt(Constants.CANTEGORY_ID, categoryId)
                homeFragment = ProductsFragment()
                homeFragment?.arguments = extras
            }
            return homeFragment!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.onCreate(arguments?.get(Constants.CANTEGORY_ID) as Int)
    }

    override fun onDestroy() {

        presenter.onDestroy()
        super.onDestroy()
    }

    override fun showLoading() {

        //  progressBar.visible()
    }

    override fun hideLoading() {

        //   progressBar.gone()
    }
}