package com.eric.alodjinha.features.product.productdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.eric.alodjinha.R
import com.eric.alodjinha.features.product.model.Product

class ProductDetailActivity : AppCompatActivity(), ProductDetailView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {

            android.R.id.home -> { onBackPressed() }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun configureViews() {

        setTitle("Corrigir nome")
        supportActionBar?.setHomeButtonEnabled(true)
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
