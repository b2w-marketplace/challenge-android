package br.com.amedigital.challenge_android.view.ui.product_detail

import android.content.DialogInterface
import android.graphics.Paint
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import br.com.amedigital.challenge_android.R
import br.com.amedigital.challenge_android.models.entity.Produto
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_product_detail.*
import kotlinx.android.synthetic.main.app_bar_home.*
import org.jetbrains.anko.backgroundColor

class ProductDetailActivity  : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        val produto = intent.getParcelableExtra("produto") as? Produto

        toolbarTitle.text = ""

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            toolbarTitle.backgroundColor = getColor(R.color.greyish)
            toolbar.backgroundColor = getColor(R.color.greyish)
            ll_navi.backgroundColor = getColor(R.color.greyish)
        }
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)



        Picasso.with(this).load(produto?.urlImagem).into(productImageDeail)
        productTitleDetail.text = produto?.descricao
        productValueFrom.text = "De: " + produto?.precoDe.toString()

        productValueFrom.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        productValueBy.text = "Por: " + produto?.precoPor.toString()

        productNameDetail.text = produto?.nome
        productDescription.text = produto?.descricao
        fab.setOnClickListener {
            var alert = AlertDialog.Builder(this)
            alert.setTitle("Produto reservado com sucesso")
            alert.setPositiveButton("ok"){ dialog, which ->
            }
            alert.show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

}