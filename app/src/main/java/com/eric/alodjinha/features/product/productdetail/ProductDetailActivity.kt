package com.eric.alodjinha.features.product.productdetail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.eric.alodjinha.R
import com.eric.alodjinha.base.Constants
import com.eric.alodjinha.features.product.model.Product
import kotlinx.android.synthetic.main.activity_product_detail.*

class ProductDetailActivity : AppCompatActivity(), ProductDetailView {

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
    }

    override fun productReservationSucess(message: String) {

    }

    override fun getProductDetail(product: Product) {

    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }
}
