package com.desafioamedigital.ui.activity.product_details

import android.os.Bundle
import com.desafioamedigital.R
import com.desafioamedigital.model.dto.Product
import com.desafioamedigital.ui.base.BaseActivity
import com.desafioamedigital.util.*
import kotlinx.android.synthetic.main.content_product_details.*
import kotlinx.android.synthetic.main.toolbar_collapsing.*
import javax.inject.Inject

class ProductDetailsActivity : BaseActivity(), ProductDetailsContract.View {

    @Inject lateinit var presenter: ProductDetailsPresenter

    private lateinit var product: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        initDagger2()
        attachPresenter()
        setProduct()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.clearDisposable()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun initDagger2() = DaggerProductDetailsComponent.builder()
        .productDetailsModule(ProductDetailsModule(this))
        .build()
        .inject(this)

    private fun attachPresenter(){
        presenter.attachView(this)
    }

    private fun setProduct(){
        product = jsonToObject(intent.getStringExtra(Extras.EXTRA_PRODUCT))
        configureToolbar(product.category.description, toolbar)
        showProductDetails(product)
        setButtonReservationListener()
    }

    private fun showProductDetails(product: Product) {
        iv_product_image.loadImage(product.imageUrl, 400)
        tv_product_name.text = product.name

        tv_price_to.text = getContext().resources
            .getString(R.string.product_details_activity_to, product.priceTo.priceDecimalFormat())

        tv_price_from.text = getContext().resources
            .getString(R.string.product_details_activity_from, product.priceFrom.priceDecimalFormat())
        tv_price_from.setStrikeThrough()

        tv_category.text = product.category.description
        tv_description.text = product.description.convertedTextFromHtml()
    }

    private fun setButtonReservationListener() {
        fab_reserve.setOnClickListener {
            presenter.reserveProduct(product.id)
        }
    }

}
