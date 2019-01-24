package com.example.b2w.activity

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.b2w.R
import com.example.b2w.model.Product
import com.example.b2w.util.BUNDLE_PRODUCT
import com.example.b2w.util.loremText
import com.example.b2w.util.loremTitle
import kotlinx.android.synthetic.main.activity_detail_category.*

class DetailCategoryActivity : AppCompatActivity() {
    private lateinit var product: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_category)

        supportActionBar?.title = ""

        product = intent.getParcelableExtra(BUNDLE_PRODUCT)

        initView()
    }

    private fun initView(){
        Glide.with(this).load(product.urlImagem).into(img_product)
        txt_product_description.text = product.nome
        txt_before_price.text = "R$ ${product.precoDe.toString()}"
        txt_after_price.text = "R$ ${product.precoPor.toString()}"
        txt_description_title.text = loremTitle
        txt_description_text.text = loremText

        btn_reserve.setOnClickListener {
            val alertDialogBuilder = AlertDialog.Builder(this)
            val message = TextView(this)

            message.text = "Produto reservado com sucesso"
            message.gravity = Gravity.CENTER
            message.setPadding(50,50,50,50)
            message.textSize = 20F
            alertDialogBuilder.setView(message).setPositiveButton("ok"){_,_->}.show()
        }
    }
}
