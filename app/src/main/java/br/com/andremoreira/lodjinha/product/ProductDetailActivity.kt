package br.com.andremoreira.lodjinha.product

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import br.com.andremoreira.lodjinha.R
import br.com.andremoreira.lodjinha.base.BaseActivity
import br.com.andremoreira.lodjinha.base.Constants
import br.com.andremoreira.lodjinha.extensions.formatFromHTML
import br.com.andremoreira.lodjinha.extensions.formatNumberReal
import br.com.andremoreira.lodjinha.extensions.loadUrl
import br.com.andremoreira.lodjinha.extensions.priceFrom
import br.com.andremoreira.lodjinha.viewmodel.ProductViewModel
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
            btnReservation.isEnabled = false
            productViewModel.productReservation(productId).observe(this, Observer { it ->
                if(it!!.isSuccess()){
                    showDialogWithCallback(
                        "",
                        getString(R.string.reservationSuccess), getString(R.string.ok), "",
                        DialogInterface.OnClickListener { dialog, which -> finish() }, null
                    )
                }
                btnReservation.isEnabled = true
            })
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