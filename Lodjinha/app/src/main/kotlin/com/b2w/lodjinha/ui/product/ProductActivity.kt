package com.b2w.lodjinha.ui.product

import android.arch.lifecycle.ViewModelProviders
import android.graphics.Paint
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.ImageView
import android.widget.TextView
import com.b2w.lodjinha.R
import com.b2w.lodjinha.data.model.ProductItem
import com.b2w.lodjinha.ui.BaseActivity
import com.b2w.lodjinha.util.bindView
import com.squareup.picasso.Picasso
import android.text.Html
import android.os.Build
import android.support.design.widget.FloatingActionButton
import android.text.Spanned
import android.view.View
import android.widget.ProgressBar
import com.b2w.lodjinha.data.model.CategoryItem
import com.b2w.lodjinha.ui.productslist.EXTRA_PRODUCT_CATEGORY
import com.b2w.lodjinha.ui.productslist.ProductsListAdapter
import com.b2w.lodjinha.ui.productslist.ProductsListViewModel
import com.b2w.lodjinha.util.networkInfo
import com.b2w.lodjinha.util.toCurrency
import android.content.DialogInterface
import android.os.Handler
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AlertDialog


const val EXTRA_PRODUCT = "EXTRA_PRODUCT"

class ProductActivity : BaseActivity() {

    val viewModel: ProductViewModel by lazy { ViewModelProviders.of(this).get(ProductViewModel::class.java) }

    val product: ProductItem by lazy { intent.getParcelableExtra<ProductItem>(EXTRA_PRODUCT) }
    val toolbar by bindView<Toolbar>(R.id.toolbar)
    val productName by bindView<TextView>(R.id.product_name)
    val productDescription by bindView<TextView>(R.id.product_description)
    val productImage by bindView<ImageView>(R.id.product_image)
    val productFromPrice by bindView<TextView>(R.id.product_from_price)
    val productToPrice by bindView<TextView>(R.id.product_to_price)
    val fab by bindView<FloatingActionButton>(R.id.fab)
    val internProgress by bindView<ConstraintLayout>(R.id.progress_bar_container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        configureToolbar()
        setTexts()
        configureFab()
    }

    fun configureToolbar() {
        toolbar.title = product.category.description
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun setTexts() {
        productName.text = product.name
        productDescription.text = fromHtml(product.description)

        Picasso.get()
            .load(product.image)
            .into(productImage)

        productFromPrice.text = getString(R.string.product_from_price, product.fromPrice.toCurrency())
        productFromPrice.paintFlags = productFromPrice.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG

        productToPrice.text = getString(R.string.product_to_price, product.toPrice.toCurrency())
    }

    fun configureFab(){
        fab.setOnClickListener({
            initObserver()
            internProgress.visibility = View.VISIBLE
            viewModel.doReservation(networkInfo(), productId = product.id)
        })
    }

    fun fromHtml(html: String): Spanned {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(html)
        }
    }

    fun initObserver(){
        viewModel.reservationResult.observeResource(this, onSuccess = {
            internProgress.visibility = View.GONE
            showDialog()
        }, onError = {
            showDefaultError()
            internProgress.visibility = View.GONE
        })
    }

    fun showDialog(){
        Handler().postDelayed({
            val dialog = AlertDialog.Builder(this, R.style.AlertDialogCustom)
            dialog.setTitle(R.string.product_reservation_success)
            dialog.setPositiveButton(android.R.string.ok) { alert, id ->
                alert.dismiss()
                internProgress.visibility = View.GONE
            }

            val alert = dialog.create()
            alert.show()
        }, 1000)

    }
}