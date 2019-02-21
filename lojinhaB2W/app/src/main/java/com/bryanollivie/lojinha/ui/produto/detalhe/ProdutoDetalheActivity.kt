package com.bryanollivie.lojinha.ui.produto.detalhe

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.bryanollivie.lojinha.R
import com.bryanollivie.lojinha.data.model.Produto
import com.bryanollivie.lojinha.ui.base.BaseActivity
import com.bryanollivie.lojinha.ui.home.adapters.ProdutoAdapter
import com.bryanollivie.lojinha.ui.produto.ProdutoListContract
import com.bryanollivie.lojinha.ui.produto.ProdutoListPresenter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_produto_detalhe.*
import kotlinx.android.synthetic.main.content_produto_detalhe.*
import kotlinx.android.synthetic.main.row_produto.view.*

class ProdutoDetalheActivity : BaseActivity<ProdutoDetalheContract.View, ProdutoDetalheContract.Presenter>(),
    ProdutoDetalheContract.View {

    override var presenter: ProdutoDetalheContract.Presenter = ProdutoDetalhePresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_produto_detalhe)
        setSupportActionBar(toolbar)

        val bundle = intent.extras
        if (bundle != null) {
            val produtoId = bundle.getString("produtoId")
            val produto = produtoId.removeSuffix(".0").toInt()
            presenter.getProduto(produto,this)
        }

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun showProduto(produto: Any) {
       /* Picasso.get()
            .load(Produto.toObject(produto)["urlImagem"].toString())
            .centerCrop()
            .fit()
            .into(imagem_produto_detalhe)*/

        produto_detalhe_title.text = Produto.toObject(produto)["nome"].toString()

    }

}
