package com.sumiya.olodjinha.ui.activity

import android.graphics.Paint
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.text.Html
import android.text.Html.fromHtml
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sumiya.olodjinha.R
import com.sumiya.olodjinha.constants.ModelKeyConstants
import com.sumiya.olodjinha.contracts.ViewProductDetailContract
import com.sumiya.olodjinha.model.ProductModel
import com.sumiya.olodjinha.presenter.ProductDetailPresenter
import com.sumiya.olodjinha.ui.activity.base.BaseActivity
import kotlinx.android.synthetic.main.activity_product_detail.*
import kotlinx.android.synthetic.main.content_product_detail.*
import java.text.NumberFormat
import java.util.*

class ProductDetailActivity : BaseActivity(), ViewProductDetailContract {

    //Variables
    private var product: ProductModel? = null
    val productDetailPresenter = ProductDetailPresenter(this)

    //Lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        product = (intent.extras.getSerializable(ModelKeyConstants.productKey) as? ProductModel)!!
        setupToolbar(product?.categoria?.descricao!!)

        configureFab()

        showLoading(resources.getString(R.string.loading_product))

        productDetailPresenter.getProduct(product?.id!!)
    }

    //Methods
    private fun configureFab() {
        fab.setOnClickListener {
            productDetailPresenter.setProductReservation(product?.id!!)
        }
    }

    fun configureUI(productDetail: ProductModel) {
        Glide
                .with(this)
                .load(productDetail.urlImagem)
                .apply(RequestOptions()
                        .placeholder(R.drawable.ic_error_outline_black_24dp))
                .into(productDetailImage)

        productTitleLabel.text = productDetail.nome
        productCategoryLabel.text = productDetail.categoria.descricao

        val format = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
        val precoDe = format.format(productDetail.precoDe)
        val precoPor = format.format(productDetail.precoPor)

        productOldPriceLabel.text = String.format(resources.getString(R.string.old_price), precoDe)
        productOldPriceLabel.paintFlags = productOldPriceLabel.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

        productPriceLabel.text = String.format(resources.getString(R.string.price), precoPor)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            productDescriptionLabel.text = fromHtml(productDetail.descricao, Html.FROM_HTML_MODE_COMPACT)
        } else {
            productDescriptionLabel.text = fromHtml(productDetail.descricao)
        }
    }

    private fun showReservationDialog(message: String) {
        val builder = AlertDialog.Builder(this@ProductDetailActivity)
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton(resources.getString(R.string.ok_button)) { _, _ ->
                    finish()
                }
        val alert = builder.create()
        alert.show()
    }

    //Contract
    override fun setProductResponse(product: ProductModel) {
        configureUI(product)
    }

    override fun showReservationResponse() {
        showReservationDialog(resources.getString(R.string.success_reservation))
    }

    override fun handleError(t: Throwable) {
        showReservationDialog(resources.getString(R.string.failure_reservation))
    }
}