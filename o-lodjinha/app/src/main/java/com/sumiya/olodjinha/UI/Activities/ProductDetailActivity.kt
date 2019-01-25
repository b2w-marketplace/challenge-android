package com.sumiya.olodjinha.UI.Activities

import android.graphics.Paint
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.text.Html
import com.bumptech.glide.Glide
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

    lateinit var product: ProductModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        fab.setOnClickListener { view ->
            val call = APIService().product().post(product.id)

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

        product = (intent.extras.getSerializable("produto") as? ProductModel)!!

        configureData()

        setupToolbar(product.categoria.descricao)
    }


    fun configureData() {
        val call = APIService().product().get(product.id)

        call.enqueue(object : Callback<ProductModel> {
            override fun onFailure(call: Call<ProductModel>?, t: Throwable?) {
                if (t != null) {
                    print(t.localizedMessage)
                }
            }

            override fun onResponse(call: Call<ProductModel>?, response: Response<ProductModel>?) {
                if (response != null) {
                    configureUI(response.body()!!)
                }
            }
        })
    }

    fun configureUI(productDetail: ProductModel) {
        Glide.with(this).load(productDetail.urlImagem).into(productDetailImage)

        productTitleLabel.text = productDetail.nome

        val format = NumberFormat.getCurrencyInstance(Locale("pt","BR"))
        val precoDe = format.format(productDetail.precoDe)
        val precoPor = format.format(productDetail.precoPor)

        productOldPriceLabel.text = "De $precoDe"
        productOldPriceLabel.setPaintFlags(productOldPriceLabel.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)

        productPriceLabel.text = "Por $precoPor"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            productDescriptionLabel.text = Html.fromHtml(productDetail.descricao, Html.FROM_HTML_MODE_COMPACT)
        } else {
            productDescriptionLabel.text = Html.fromHtml(productDetail.descricao)
        }
//
//        val toolbar = findViewById<View>(R.id.prod_toolbar) as Toolbar
//        setSupportActionBar(toolbar)
//        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
//        supportActionBar!!.setDisplayShowHomeEnabled(true)
//        toolbar.setNavigationOnClickListener(object : View.OnClickListener {
//            override fun onClick(v: View) {
//                finish()
//            }
//        })
    }
}
