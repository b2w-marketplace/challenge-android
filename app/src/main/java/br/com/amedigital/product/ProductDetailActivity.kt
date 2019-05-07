package br.com.amedigital.product

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.app.AlertDialog
import android.text.Html
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import br.com.amedigital.R
import br.com.amedigital.model.Product
import br.com.amedigital.network.LojinhaServiceEndPoint
import br.com.amedigital.util.formatToMoney
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product_detail.*
import kotlinx.android.synthetic.main.partial_product_detail.*

class ProductDetailActivity : AppCompatActivity(), ProductContract.ViewReserveProduct {

    companion object {
        const val PRODUCT = "product"
    }

    var product : Product? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
        getDataExtra(intent.extras)
        setCollapsingButtonIcon()
        buttonSaveProduct()
    }

    private fun getDataExtra(extras: Bundle?) {
        product = extras?.getSerializable(PRODUCT) as Product
        product?.apply {
            setToolbar()
            bindProduct(this)
        }
    }

    private fun setToolbar(){
        toolbar.title = ""
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24px)
        setSupportActionBar(toolbar)
    }

    private fun setCollapsingButtonIcon() {
        app_bar.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            var isShow = false
            var scrollRange = -1

            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    isShow = true
                    toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24px)
                } else if (isShow) {
                    isShow = false
                    toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24px)
                }
            }
        })
    }

    private fun reserveProduct() {
        product?.apply {
            val call = LojinhaServiceEndPoint().setReservation(id)
            val presenter = ProductPresenter()
            presenter.reserve(call, this@ProductDetailActivity)
        }
    }

    private fun bindProduct(product: Product) {
        Picasso.get()
            .load(product.urlImage)
            .into(imageViewProductDesc)
        textViewCategoryName.text = product.category?.description
        textViewDe.text = "De ${product.priceOf.formatToMoney()}"
        textViewPor.text = "Por ${product.priceFor.formatToMoney()}"
        textViewProdName.text = product.name
        textViewDescProd.text = Html.fromHtml(product.description)
    }

    private fun buttonSaveProduct() {
        floatingButtonReserve.setOnClickListener {
            floatingButtonReserve.isEnabled = false
            reserveProduct()
        }
    }

    override fun setProgressReserve(active: Boolean) {
        floatingButtonReserve.isEnabled = !active
        progressBarReserve.visibility = if (active) View.VISIBLE else View.GONE
    }

    override fun successReserve() {
        val builder = AlertDialog.Builder(this)
            builder.setCancelable(false)
            builder.setMessage(getString(R.string.success_reserve))
            builder.setPositiveButton(getString(R.string.ok)){ dialog, which ->
                dialog.dismiss()
                finish()
            }
            builder.create().show()
    }

    override fun showErrorReserve(message: String) {
        Toast.makeText(this@ProductDetailActivity, message, Toast.LENGTH_LONG).show()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}

