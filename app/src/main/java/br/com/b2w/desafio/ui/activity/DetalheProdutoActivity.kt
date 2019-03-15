package br.com.b2w.desafio.ui.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.DialogInterface
import android.databinding.DataBindingUtil
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.text.Html
import android.view.MenuItem
import br.com.b2w.desafio.R
import br.com.b2w.desafio.databinding.ActivityDetalheProdutoBinding
import br.com.b2w.desafio.model.produto.Produto
import br.com.b2w.desafio.model.response.LodjinhaResponse
import br.com.b2w.desafio.ui.viewmodel.ProdutoViewModel
import br.com.b2w.desafio.util.Alert
import com.bumptech.glide.Glide
import android.graphics.Paint
import br.com.b2w.desafio.util.Utils
import java.text.NumberFormat


class DetalheProdutoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetalheProdutoBinding

    private val produtoViewModel: ProdutoViewModel by lazy {
        ViewModelProviders.of(this).get(ProdutoViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detalhe_produto)

        setToolbar()
        title = getProdutoSelecionado().categoria!!.descricao

        binding.fab.setOnClickListener { view ->
            binding.fab.isEnabled = false
            produtoViewModel.postById(getProdutoSelecionado().id!!)
        }

        addObservable()
        carregarProduto()
    }

    private fun setToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun addObservable() {
        produtoViewModel.getPostByIdObservable().observe(this, Observer<LodjinhaResponse<Produto>> { produtoResponse ->

            binding.fab.isEnabled = true

            if (produtoResponse != null) {
                if (!produtoResponse.result.isNullOrEmpty()) {
                    Alert.showDialogOk(getString(R.string.msg_sucesso_reserva_produto), this@DetalheProdutoActivity, onClickListenerOk)
                } else {
                    Alert.showDialogOk(produtoResponse.message!!, this@DetalheProdutoActivity, onClickListenerOk)
                }
            } else {
                Alert.showDialogOk(getString(R.string.msg_erro_produto_por_categoria), this@DetalheProdutoActivity, onClickListenerOk)
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

    private fun getProdutoSelecionado(): Produto {
        val bundle = intent.extras
        return bundle.getParcelable(getString(R.string.param_produto)) as Produto
    }

    private fun carregarProduto(){
        val produto = getProdutoSelecionado()
        if(produto != null){
            Glide
                .with(this@DetalheProdutoActivity)
                .load(produto.urlImagem)
                .placeholder(R.drawable.loading)
                .error(R.mipmap.ic_nao_disponivel)
                .into(binding.contentDetalheProduto.ivProduto)

            val defaultFormat = NumberFormat.getInstance(Utils.getCurrentLocale(this@DetalheProdutoActivity))

            binding.contentDetalheProduto.tvDescricao.text = produto.nome
            binding.contentDetalheProduto.tvPrecoDe.text = "De " + defaultFormat.format(produto.precoDe)
            binding.contentDetalheProduto.tvPrecoDe.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            binding.contentDetalheProduto.tvPrecoPor.text = "Por " + defaultFormat.format(produto.precoPor)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                binding.contentDetalheProduto.tvDescricaoCompleta.text = Html.fromHtml(produto.descricao, Html.FROM_HTML_MODE_LEGACY)
            } else {
                @Suppress("DEPRECATION")
                binding.contentDetalheProduto.tvDescricaoCompleta.text = (Html.fromHtml(produto.descricao))
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> false
    }
}
