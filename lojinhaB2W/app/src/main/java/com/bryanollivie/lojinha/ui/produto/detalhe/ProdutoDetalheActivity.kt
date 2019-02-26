package com.bryanollivie.lojinha.ui.produto.detalhe

import android.graphics.Paint
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.text.Html
import com.bryanollivie.lojinha.R
import com.bryanollivie.lojinha.data.model.Produto
import com.bryanollivie.lojinha.ui.base.BaseActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_produto_detalhe.*
import kotlinx.android.synthetic.main.activity_produto_list.*
import kotlinx.android.synthetic.main.row_produto.view.*

class ProdutoDetalheActivity : BaseActivity<ProdutoDetalheContract.View, ProdutoDetalheContract.Presenter>(),
    ProdutoDetalheContract.View {


    override var presenter: ProdutoDetalheContract.Presenter = ProdutoDetalhePresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_produto_detalhe)
        setSupportActionBar(toolbar_produto_detalhe)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val bundle = intent.extras
        if (bundle != null) {
            val produtoId = bundle.getString("produtoId")
            val produto = produtoId.removeSuffix(".0").toInt()
            presenter.getProduto(produto, this)
        }


    }

    override fun showProduto(produto: Produto) {


        Picasso.get()
            .load(produto.urlImagem)
            .centerInside()
            .error(R.drawable.baseline_perm_media_24)
            .fit()
            .into(imagem_produto_detalhe)

        produto_detalhe_title.text = produto.nome
        produto_detalhe_de.text = "De: ${produto.precoDe.toString().replace(".", ",")}"
        produto_detalhe_por.text = "Por: ${produto.precoPor.toString().replace(".", ",")}"
        produto_detalhe_desc.text = Html.fromHtml(produto.descricao!!).toString();

        produto_detalhe_de.setPaintFlags(produto_detalhe_de.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)

        fab.setOnClickListener {
            presenter.reserverProduto(produto.id!!, this)
        }

    }

    override fun showMsg(msg: String) {
        Snackbar.make(produto_detalhe_layout, "Produto Reservado!", Snackbar.LENGTH_LONG)
            .setAction("ok", null).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }


}
