package br.com.andreguedes.alodjinha.ui.product.detail

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.text.Html
import android.view.MenuItem
import br.com.andreguedes.alodjinha.R
import br.com.andreguedes.alodjinha.data.model.Product
import br.com.andreguedes.alodjinha.helper.StringHelper
import br.com.andreguedes.alodjinha.ui.base.BaseActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_product_detail.*
import android.widget.Toast



class ProductDetailActivity : BaseActivity(), ProductDetailContract.View {

    override lateinit var presenter: ProductDetailContract.Presenter

    private lateinit var product: Product

    companion object {

        const val EXTRA_PRODUCT = "br.com.andreguedes.alodjinha.ui.product.detail.ProductDetailActivity.EXTRA_PRODUCT"

        fun getStartIntent(context: Context, product: Product): Intent {
            val intent = Intent(context, ProductDetailActivity::class.java)
            intent.putExtra(EXTRA_PRODUCT, product)
            return intent
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        product = intent.getSerializableExtra(EXTRA_PRODUCT) as Product

        initToolbar(R.id.toolbar_normal)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(true)

        collapsing_toolbar.setExpandedTitleColor(resources.getColor(android.R.color.transparent))
        collapsing_toolbar.setCollapsedTitleTextColor(resources.getColor(android.R.color.white))

        app_bar_layout.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            val icon = toolbar.navigationIcon
            if (verticalOffset == 0) {
                icon!!.setColorFilter(resources.getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP)
            } else {
                icon!!.setColorFilter(resources.getColor(android.R.color.white), PorterDuff.Mode.SRC_ATOP)
            }
        }

        initUI()
        addListeners()
    }

    override fun onResume() {
        super.onResume()

        supportActionBar!!.title = product.categoria!!.descricao

        Glide.with(this).load(product.urlImagem).error(R.drawable.logo_menu).into(img_product)

        txt_product_name.text = product.nome
        txt_price_from.text = String.format(getString(R.string.price_from),
                StringHelper.formatCurrencyNew(product.precoDe!!))
        txt_price_to.text = String.format(getString(R.string.price_to),
                StringHelper.formatCurrencyNew(product.precoPor!!))
        txt_product_description.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            Html.fromHtml(product.descricao, Html.FROM_HTML_MODE_COMPACT) else Html.fromHtml(product.descricao)
    }

    override fun onPause() {
        super.onPause()

        presenter.unsubscribe()
    }

    override fun initUI() {
        presenter = ProductDetailPresenter(this, product.id!!)
    }

    override fun addListeners() {
        fab_reserve.setOnClickListener {
            presenter.subscribe()
            fab_progress_circle.show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    override fun reservedProduct() {
        fab_progress_circle.beginFinalAnimation()

        val dialog = AlertDialog.Builder(this)
        dialog.setMessage(getString(R.string.reserved_product))
        dialog.setCancelable(false)
        dialog.setPositiveButton(getString(R.string.OK), DialogInterface.OnClickListener { dialog, _ ->
            dialog.dismiss()
            finish()
        })
        dialog.show()
    }

}