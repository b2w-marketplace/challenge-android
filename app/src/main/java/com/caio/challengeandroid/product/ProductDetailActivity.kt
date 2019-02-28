package com.caio.challengeandroid.product

import android.content.DialogInterface
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import com.caio.challengeandroid.R
import com.caio.lodjinha.base.BaseActivity
import com.caio.lodjinha.base.Constants
import com.caio.lodjinha.extensions.formatFromHTML
import com.caio.lodjinha.extensions.formatNumberReal
import com.caio.lodjinha.extensions.loadUrl
import com.caio.lodjinha.extensions.priceFrom
import com.caio.lodjinha.viewmodel.ProductDetailViewModel
import kotlinx.android.synthetic.main.activity_detail_product.*
import kotlinx.android.synthetic.main.include_product_description_detail.*
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class ProductDetailActivity : BaseActivity() {

    private val productId: Int by lazy { intent.getIntExtra(Constants.PRODUCT_ID,0)}

    private val categoryName: String? by lazy { intent.getStringExtra(Constants.CATEGORY_NAME)}

    private val productDetailViewModel: ProductDetailViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_product)
        setupView()
        observerProductDetail()
    }

    private fun setupView() {
        setSupportActionBar(toolbar)
        title = categoryName
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btnReservation.setOnClickListener {
            observerProductReservation()
        }
    }

    private fun observerProductReservation() {
        btnReservation.isEnabled = false
        launch { productDetailViewModel.productReservation(productId) }
        productDetailViewModel.reservationProductLiveData.observe(this, Observer { reserv->
            when (reserv) {
                is ProductDetailViewModel.ReservationProductState.SuccessState ->
                    showDialogWithCallback(
                        "",
                        getString(R.string.reservationSuccess), getString(R.string.ok), "",
                        DialogInterface.OnClickListener { dialog, which -> finish() }, null
                    )

                is ProductDetailViewModel.ReservationProductState.ErrorState ->
                    showDialogWithCallback(
                        "",
                        reserv.message, getString(R.string.ok), "",
                        DialogInterface.OnClickListener { dialog, which -> }, null
                    )
            }
            btnReservation.isEnabled = true
        })
    }

    private fun observerProductDetail() {
        launch { productDetailViewModel.getProductDetail(productId) }
        productDetailViewModel.productLiveData.observe(this, Observer { prod->
            ivProductDetail.loadUrl(prod.urlImagem,progressBarDetail)
            tvProductNameDetail.text = prod.nome
            tvPriceFromDetail.text = prod.precoDe.formatNumberReal()
            tvPriceFromDetail.priceFrom()
            tvPriceByDetail.text = prod.precoPor.formatNumberReal()
            tvDescriptionDetail.text = prod.descricao.formatFromHTML()
        })
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {

            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}