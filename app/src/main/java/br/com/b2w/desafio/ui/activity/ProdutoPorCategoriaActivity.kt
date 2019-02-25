package br.com.b2w.desafio.ui.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.DialogInterface
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import br.com.b2w.desafio.databinding.ActivityProdutoPorCategoriaBinding
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import android.view.View
import android.widget.AbsListView
import br.com.b2w.desafio.R
import br.com.b2w.desafio.model.Categoria
import br.com.b2w.desafio.model.produto.Produto
import br.com.b2w.desafio.model.response.LodjinhaResponse
import br.com.b2w.desafio.ui.adapter.ProdutoAdapter
import br.com.b2w.desafio.ui.viewmodel.ProdutoViewModel
import br.com.b2w.desafio.util.Alert

class ProdutoPorCategoriaActivity : AppCompatActivity(), ProdutoAdapter.AgendaAdapterOnClickListener {

    private lateinit var binding: ActivityProdutoPorCategoriaBinding

    private var produtoViewModel: ProdutoViewModel? = null

    private lateinit var produtoAdapter: ProdutoAdapter

    private var linearLayoutManager: LinearLayoutManager? = null

    private var isScrolling: Boolean? = false
    private var currentItems: Int = 0
    private var totalItems:Int = 0
    private var scrollOutItems:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_produto_por_categoria)

        setToolbar()
        title = getCategoriaSelecionada().descricao

        produtoViewModel = ViewModelProviders.of(this).get(ProdutoViewModel::class.java)

        binding.rvProdutos.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this@ProdutoPorCategoriaActivity)
        binding.rvProdutos.layoutManager = linearLayoutManager
        produtoAdapter = ProdutoAdapter(this@ProdutoPorCategoriaActivity, this)
        binding.rvProdutos.adapter = produtoAdapter

        addOnScrollListener()
        addObservable()

        produtoViewModel!!.listar(0, 20, getCategoriaSelecionada().id)
    }

    private fun setToolbar() {
        setSupportActionBar(binding.toolbar.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun getCategoriaSelecionada(): Categoria {
        val bundle = intent.extras
        return bundle.getParcelable(getString(R.string.param_categoria)) as Categoria
    }

    private fun addOnScrollListener(){
        binding.rvProdutos.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                currentItems = linearLayoutManager!!.childCount
                totalItems = linearLayoutManager!!.itemCount
                scrollOutItems = linearLayoutManager!!.findFirstVisibleItemPosition()

                if (isScrolling!! && currentItems + scrollOutItems === totalItems) {
                    isScrolling = false

                    if (dy > 0) {
                        // Scrolling up
                        val produto = produtoAdapter.getItemByPosition(totalItems - 1)

                        binding.pbLoading.visibility = View.VISIBLE
                        produtoViewModel!!.listar(produto.id!!, 20, getCategoriaSelecionada().id)
                    } else {
                        // Scrolling down
                    }
                }
            }
        })
    }

    private fun addObservable() {
        produtoViewModel!!.listarObservable()
            .observe(this, Observer<LodjinhaResponse<MutableList<Produto>>> { produtoResponse ->

                binding.pbLoading.visibility = View.GONE
                if (produtoResponse != null) {
                    if (produtoResponse.message.isNullOrEmpty()) {
                        if(!produtoResponse.data!!.isEmpty()){
                            produtoAdapter.addData(produtoResponse.data!!)
                        } else {

                            if(produtoAdapter.itemCount == 0)
                                Alert.showDialogOk(getString(R.string.msg_indisponibilidade_produto_por_categoria), this@ProdutoPorCategoriaActivity, onClickListenerOk)
                        }
                    } else {
                        Alert.showDialogOk(produtoResponse.message, this@ProdutoPorCategoriaActivity, onClickListenerOk)
                    }
                } else {
                    Alert.showDialogOk(getString(R.string.msg_erro_produto_por_categoria), this@ProdutoPorCategoriaActivity, onClickListenerOk)
                }
            })
    }

    private var onClickListenerOk: DialogInterface.OnClickListener = DialogInterface.OnClickListener { dialog, which ->
        when (which) {
            DialogInterface.BUTTON_POSITIVE -> {
                dialog.dismiss()

                finish()
            }
        }
    }

    override fun onClickProduto(produto: Produto?) {
        val intent = Intent(this@ProdutoPorCategoriaActivity, DetalheProdutoActivity::class.java)
        intent.putExtra(getString(R.string.param_produto), produto)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> false
    }
}