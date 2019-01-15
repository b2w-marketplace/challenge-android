package alodjinha.cfgdemelo.com.view.product

import alodjinha.cfgdemelo.com.model.Product
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import alodjinha.cfgdemelo.com.view.R
import alodjinha.cfgdemelo.com.view.adapter.toMoney
import alodjinha.cfgdemelo.com.viewmodel.product.ProductViewModel
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Paint
import android.os.Build
import android.support.v7.app.AlertDialog
import android.text.Html
import android.view.MenuItem
import android.view.View
import com.squareup.picasso.Picasso
import io.reactivex.disposables.CompositeDisposable

import kotlinx.android.synthetic.main.activity_product.*
import kotlinx.android.synthetic.main.content_product.*

class ProductActivity : AppCompatActivity() {

    private val productViewModel by lazy { ProductViewModel() }
    private val compositeDisposable by lazy { CompositeDisposable() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = null
        val productId = intent.getIntExtra(PRODUCT_ID, 0)
        val productImageUrl = intent.getStringExtra(PRODUCT_IMAGE_URL)
        initUi(productImageUrl, productId)
        getProductInfo(productId)
        startErrorObservable()
        startBookingObservable()
    }

    private fun initUi(productImageUrl: String, productId: Int) {
        Picasso.with(this)
            .load(productImageUrl)
            .into(ivProduct)

        fabReserve.setOnClickListener {
            fabReserve.isClickable = false
            Thread {
                productViewModel.bookProduct(productId)
            }.start()
        }
    }

    private fun getProductInfo(productId: Int) {
        Thread {
            productViewModel.getProductInfo(productId)
        }.start()
        productViewModel.productObservable.subscribe {
            runOnUiThread {
                setProductInfo(it)
            }
        }.let { compositeDisposable.add(it) }
    }

    private fun setProductInfo(product: Product) {
        tvProduct.text = product.name
        productPriceFrom.text = product.priceFrom.toMoney()
        productPriceFrom.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        productPriceFor.text = product.priceFor.toMoney()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tvDescription.text = Html.fromHtml(product.description, Html.FROM_HTML_MODE_COMPACT)
        } else {
            tvDescription.text = Html.fromHtml(product.description)
        }
        Picasso.with(this)
            .load(product.imageUrl)
            .into(ivProduct)
        pbProduct.visibility = View.GONE
    }

    private fun startErrorObservable() {
        productViewModel.productErrorObservable.subscribe {
            Snackbar.make(fabReserve, getString(R.string.tryAgainLater), Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }.let { compositeDisposable.add(it) }
    }

    private fun startBookingObservable() {
        productViewModel.bookingObservable.subscribe {
            runOnUiThread {
                val listener = DialogInterface.OnClickListener { _, _ ->
                    finish()
                }
                val dialog = AlertDialog.Builder(this)
                dialog.setCancelable(false)
                dialog.setPositiveButton(R.string.ok, listener)
                if (it) {
                    dialog.setMessage(R.string.bookingProductSuccess)
                } else {
                    dialog.setMessage(R.string.bookingProductFail)
                }
                dialog.create().show()
                fabReserve.isClickable = true
            }
        }.let { compositeDisposable.add(it) }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        when (id) {
            HOME -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        fun getActivityIntent(context: Context, productId: Int, productImageUrl: String): Intent {
            val intent = Intent(context, ProductActivity::class.java)
            intent.putExtra(PRODUCT_ID, productId)
            intent.putExtra(PRODUCT_IMAGE_URL, productImageUrl)
            return intent
        }

        private const val PRODUCT_ID = "productId"
        private const val PRODUCT_IMAGE_URL = "productImageUrl"
        private const val HOME = 16908332
    }

}
