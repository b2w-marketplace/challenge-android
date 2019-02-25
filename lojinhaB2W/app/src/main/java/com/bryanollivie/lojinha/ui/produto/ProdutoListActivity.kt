package com.bryanollivie.lojinha.ui.produto

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.bryanollivie.lojinha.R
import com.bryanollivie.lojinha.ui.base.BaseActivity
import com.bryanollivie.lojinha.ui.home.adapters.ProdutoAdapter
import com.bryanollivie.lojinha.ui.produto.detalhe.ProdutoDetalheActivity
import kotlinx.android.synthetic.main.activity_produto_list.*

class ProdutoListActivity : BaseActivity<ProdutoListContract.View, ProdutoListContract.Presenter>(),
    ProdutoListContract.View {

    var produtoAdapter: ProdutoAdapter? = null
    override var presenter: ProdutoListContract.Presenter = ProdutoListPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_produto_list)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val bundle = intent.extras

        if (bundle != null) {
            val categoriaId = bundle.getString("categoriaId")

            presenter.getProdutos(categoriaId.removeSuffix(".0").toInt(), this)
        }

    }

    override fun showProdutos(produtos: List<Any>) {

        if (produtos.size > 0) {
            rvProduto.setHasFixedSize(true)
            rvProduto.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

            produtoAdapter = ProdutoAdapter(produtos) { itemClick ->
                val intent = Intent(this, ProdutoDetalheActivity::class.java)
                intent.putExtra("produtoId", itemClick)
                startActivity(intent)
            }
            rvProduto.adapter = produtoAdapter
            rvProduto.visibility = View.VISIBLE
        } else {
            sem_produtos.visibility = View.VISIBLE
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
