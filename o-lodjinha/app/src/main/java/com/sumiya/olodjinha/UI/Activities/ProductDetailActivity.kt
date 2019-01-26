package com.sumiya.olodjinha.UI.Activities

import android.graphics.Paint
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.text.Html
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sumiya.olodjinha.Model.ProductModel
import com.sumiya.olodjinha.Model.ReservationModel
import com.sumiya.olodjinha.R
import com.sumiya.olodjinha.Service.APIService
import com.sumiya.olodjinha.UI.Activities.Base.BaseActivity
import kotlinx.android.synthetic.main.activity_product_detail.*
import kotlinx.android.synthetic.main.content_product_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.NumberFormat
import java.util.*

class ProductDetailActivity : BaseActivity() {
    var product: ProductModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        product = (intent.extras.getSerializable("produto") as? ProductModel)!!
        setupToolbar(product?.categoria?.descricao!!)

        configureFab()

        showLoading("Carregando Produto")

        configureData()
    }

    fun configureFab() {
        fab.setOnClickListener { view ->
            val call = APIService().product().post(product?.id!!)

            call.enqueue(object : Callback<ReservationModel> {
                override fun onFailure(call: Call<ReservationModel>?, t: Throwable?) {
                    if (t != null) {
                        print(t.localizedMessage)
                    }
                }

                override fun onResponse(call: Call<ReservationModel>?, response: Response<ReservationModel>?) {
                    if (response != null) {
                        val builder = AlertDialog.Builder(this@ProductDetailActivity)
                        builder.setMessage("Produto reservado com sucesso")
                                .setCancelable(false)
                                .setPositiveButton("OK") { dialog, id ->
                                    finish()
                                }
                        val alert = builder.create()
                        alert.show()
                    }
                }
            })
        }
    }

    fun configureData() {
        val call = APIService().product().get(product?.id!!)

        call.enqueue(object : Callback<ProductModel> {
            override fun onFailure(call: Call<ProductModel>?, t: Throwable?) {
                hideLoading()
                if (t != null) {
                    print(t.localizedMessage)
                }
            }

            override fun onResponse(call: Call<ProductModel>?, response: Response<ProductModel>?) {
                hideLoading()
                if (response != null) {
                    configureUI(response.body()!!)
                }
            }
        })
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

        val format = NumberFormat.getCurrencyInstance(Locale("pt","BR"))
        val precoDe = format.format(productDetail.precoDe)
        val precoPor = format.format(productDetail.precoPor)

        productOldPriceLabel.text = "De $precoDe"
        productOldPriceLabel.paintFlags = productOldPriceLabel.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

        productPriceLabel.text = "Por $precoPor"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            productDescriptionLabel.text = Html.fromHtml(productDetail.descricao, Html.FROM_HTML_MODE_COMPACT)
        } else {
            productDescriptionLabel.text = Html.fromHtml(productDetail.descricao)
        }
    }
}