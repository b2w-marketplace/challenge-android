package com.bryanollivie.lojinha.ui.produto.detalhe

import android.os.Bundle
import android.support.design.widget.Snackbar
import com.bryanollivie.lojinha.R
import com.bryanollivie.lojinha.data.model.Produto
import com.bryanollivie.lojinha.ui.base.BaseActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_produto_detalhe.*

class ProdutoDetalheActivity : BaseActivity<ProdutoDetalheContract.View, ProdutoDetalheContract.Presenter>(),
    ProdutoDetalheContract.View {


    override var presenter: ProdutoDetalheContract.Presenter = ProdutoDetalhePresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_produto_detalhe)
        //setSupportActionBar(collapsing_produto_detalhe)

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
            .centerCrop()
            .fit()
            .into(imagem_produto_detalhe)

        produto_detalhe_title.text = produto.nome
        produto_detalhe_de.text = produto.precoDe.toString().replace(".", ",")
        produto_detalhe_por.text = produto.precoPor.toString().replace(".", ",")
        produto_detalhe_desc.text = produto.descricao

        fab.setOnClickListener {
            presenter.reserverProduto(produto.id!!, this)
        }

    }

    override fun showMsg(msg: String) {
        Snackbar.make(produto_detalhe_layout, "${msg}: Reservado!", Snackbar.LENGTH_LONG)
            .setAction("ok", null).show()
    }

}
