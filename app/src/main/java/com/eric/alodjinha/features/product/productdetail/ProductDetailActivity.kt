package com.eric.alodjinha.features.product.productdetail

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.eric.alodjinha.R
import com.eric.alodjinha.base.BaseActivity
import com.eric.alodjinha.base.Constants
import com.eric.alodjinha.features.product.api.ProductReservationResponse
import com.eric.alodjinha.features.product.model.Product
import kotlinx.android.synthetic.main.activity_product_detail.*

class ProductDetailActivity : BaseActivity(), ProductDetailView {

    val presenter: ProductDetailPresenter = ProductDetailPresenterImpl(this)
    var productId = 0
    var productName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        productId = intent.getIntExtra(Constants.PRODUCT_ID, 0)
        productName = intent.getStringExtra(Constants.CATEGORY_NAME)
        presenter.onCreate(productId)
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    companion object {

        fun starter(context: Context, productId: Int, categoryName: String) {

            val intent = Intent(context, ProductDetailActivity::class.java)
            intent.putExtra(Constants.PRODUCT_ID, productId)
            intent.putExtra(Constants.CATEGORY_NAME, categoryName)
            context?.startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {

            android.R.id.home -> {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun configureViews() {

        setSupportActionBar(toolbar)
        setTitle(productName)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        floatingActionButtom.setOnClickListener { presenter.productReservation(productId) }
    }

    override fun productReservationSucess(message: ProductReservationResponse) {

        showDialogWithCallback("",
            getString(R.string.product_reservation_suscess), getString(R.string.close_buttom), "",
            DialogInterface.OnClickListener { dialog, which -> finish() }, null
        )
    }

    override fun productReservationError() {

        showDialogWithCallback("",
            getString(R.string.product_reservation_error), getString(R.string.ok), "",
            DialogInterface.OnClickListener { dialog, which ->  }, null
        )
    }

    override fun getProductDetail(product: Product) {

    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }
}
