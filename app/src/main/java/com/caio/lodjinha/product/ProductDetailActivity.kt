package com.caio.lodjinha.product

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.caio.lodjinha.R
import com.caio.lodjinha.base.BaseActivity
import com.caio.lodjinha.base.Constants
import com.caio.lodjinha.extensions.formatFromHTML
import com.caio.lodjinha.extensions.formatNumberReal
import com.caio.lodjinha.extensions.loadUrl
import com.caio.lodjinha.extensions.priceFrom
import com.caio.lodjinha.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.activity_detail_product.*
import kotlinx.android.synthetic.main.include_product_description_detail.*

class ProductDetailActivity : BaseActivity() {

    private val TAG: String = this::class.java.simpleName

    private val productId: Int by lazy { intent.getIntExtra(Constants.PRODUCT_ID,0)}
    private val categoryName: String? by lazy { intent.getStringExtra(Constants.CATEGORY_NAME)}

    private lateinit var productViewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detail_product)
        Log.d(TAG,"productId->"+productId)

        initViewModel()

        setupView()

        getProductDetail()
    }

    private fun getProductDetail() {

        productViewModel.getProductDetail(productId).observe(this, Observer {
            if(it!!.isSuccess()){
                val product = it.data

                ivProductDetail.loadUrl(product!!.urlImagem,progressBarDetail)

                tvProductNameDetail.text = product.nome
                tvPriceFromDetail.text = product.precoDe?.formatNumberReal()
                tvPriceFromDetail.priceFrom()
                tvPriceByDetail.text = product.precoPor?.formatNumberReal()
                tvDescriptionDetail.text = product.descricao?.formatFromHTML()

            }
        })
    }

    private fun setupView() {
        setSupportActionBar(toolbar)
        setTitle(categoryName)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btnReservation.setOnClickListener {
//            presenter.productReservation(productId)
//            floatingActionButtom.isEnabled = false
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {

            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initViewModel() {
        productViewModel = ViewModelProviders.of(this).get(ProductViewModel::class.java)
    }
}