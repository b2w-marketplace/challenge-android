package com.example.lidjinha.lodjinha.ui.product

import android.app.ProgressDialog
import android.graphics.Paint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.Toast
import com.example.lidjinha.lodjinha.R
import com.example.lidjinha.lodjinha.data.usecase.ProductsUseCase
import com.example.lidjinha.lodjinha.model.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product.*

class ProductActivity : AppCompatActivity(), ProductContract.View {

    companion object {
        val EXTRA_PRODUCT = "extra_product"
    }

    private val FOR_VALUE = "Por "
    private val BY_VALUE = "De: "

    lateinit var progress: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        val bundle = intent.extras
        val product = bundle.getSerializable(EXTRA_PRODUCT) as Product

        val productImage = iv_product
        Picasso.with(this).load(product.urlImage).into(productImage)

        setupToolbar(product.name)

        val useCase = ProductsUseCase()
        val presenter = ProductPresenter(this, useCase)

        val fullPrice = tv_full_price
        fullPrice.text = BY_VALUE + String.format("%.2f", product.fullPrice).replace(".", ".")
        fullPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

        val salePrice = tv_sale_price
        salePrice.text = FOR_VALUE + String.format("%.2f", product.salePrice).replace(".", ".")

        val theme = tv_theme
        theme.text = product.category.description

        val description = tv_description
        description.text = product.description

        val floatingButton = fab_floating_button
        floatingButton.setOnClickListener { presenter.getReserve(product.id) }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showReserveMessage(text: Int) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }

    override fun showProgress() {
        progress = ProgressDialog.show(this, getString(R.string.wait_please), getString(R.string.searching_informations))
    }

    override fun hideProgress() {
        progress.dismiss()
    }

    private fun setupToolbar(title: String) {
        val collapsingToolbarLayout = ctl_collapsing_toolbar
        collapsingToolbarLayout.title = title

        val toolbar: Toolbar = t_toolbar
        toolbar.title = title
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}