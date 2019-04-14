package br.com.dafle.alodjinha.ui.products.details

import android.graphics.Paint
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.view.View
import androidx.lifecycle.Observer
import br.com.dafle.alodjinha.R
import br.com.dafle.alodjinha.base.BaseActivity
import br.com.dafle.alodjinha.model.Product
import br.com.dafle.alodjinha.util.toCoin
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_product_details.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.okButton
import org.koin.android.viewmodel.ext.android.viewModel

class ProductDetails : BaseActivity() {

    val viewModel: ProductDetailsViewModel by viewModel()
    private var productId: Int? = null

    companion object {
        val EXTRA_PRODUCT = "EXTRA_PRODUCT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)
        intent.extras?.getParcelable<Product>(EXTRA_PRODUCT)?.let {
            productId = it.id
            title = it.categoria.descricao
            viewModel.fetch(it.id)
        }
        swipeRefresh.setOnRefreshListener { productId?.let { viewModel.fetch(it) } }

        viewModel.progress.observe(this, Observer {
            progessView.visibility = if (it) View.VISIBLE else View.GONE
            swipeRefresh.isRefreshing = false
        })

        viewModel.product.observe(this, Observer {
            Glide.with(this).load(it.urlImagem).placeholder(R.drawable.placeholder).into(ivProduct)
            tvProductName.text = it.nome
            tvFromValue.paintFlags = tvFromValue.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            tvFromValue.text = it.precoDe.toCoin()
            tvToValue.text = it.precoPor.toCoin()
            tvDescription.text = it.descricao.formatToHtml()
        })
        floatingActionButton.setOnClickListener {
            productId?.let {
                progessView.visibility = View.VISIBLE
                viewModel.reserve(it)
            }
        }

        viewModel.errorState.observe(this, Observer {
            alert { title = it; okButton { it.dismiss() } }.also {
                setTheme(R.style.AlertDialogTheme)
            }.show()
        })

        viewModel.reserve.observe(this, Observer { response ->
            alert {
                title = response.first
                isCancelable = false
                also {
                    setTheme(R.style.AlertDialogTheme)
                }
                okButton {
                    it.dismiss()
                    if (response.second) {
                        finish()
                    }
                }
            }.show()
        })
    }

    private fun String.formatToHtml(): Spanned {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
        else Html.fromHtml(this)
    }
}
