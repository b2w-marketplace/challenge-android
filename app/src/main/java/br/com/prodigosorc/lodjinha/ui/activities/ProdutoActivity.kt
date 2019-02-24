package br.com.prodigosorc.lodjinha.ui.activities

import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import br.com.prodigosorc.lodjinha.R
import br.com.prodigosorc.lodjinha.models.Produto
import br.com.prodigosorc.lodjinha.ui.fragments.ProdutoDetalheFragment
import br.com.prodigosorc.lodjinha.ui.fragments.ProdutoFragment
import br.com.prodigosorc.lodjinha.util.Constants.Companion.DESCRICAO_CATEGORIA
import br.com.prodigosorc.lodjinha.util.Constants.Companion.PRODUTO_DETALHE

class ProdutoActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_produto)
        val bundle: Bundle? = intent.extras
        carregaProdutoDetalhe(bundle)
        carregaListaProdutos(bundle)
    }

    private fun carregaProdutoDetalhe(bundle: Bundle?) {
        if (bundle?.get(PRODUTO_DETALHE) != null) {
            val produtoDetalhe = bundle.get(PRODUTO_DETALHE) as Produto
            iniciaProdutoDetalheFragment(fragmentTransaction(), produtoDetalhe)
        }
    }

    private fun carregaListaProdutos(bundle: Bundle?) {
        if (bundle?.get(DESCRICAO_CATEGORIA) != null) {
            val descricaoCategoria = bundle.get(DESCRICAO_CATEGORIA) as String
            iniciaProdutoListaFragment(fragmentTransaction(), descricaoCategoria)
        }
    }

    private fun fragmentTransaction(): FragmentTransaction {
        val fragmentManager = supportFragmentManager
        val tx = fragmentManager.beginTransaction()
        return tx
    }

    private fun iniciaProdutoDetalheFragment(
        fragmentTransaction: FragmentTransaction,
        produto: Produto
    ) {
        val produtoDetalheFragment = ProdutoDetalheFragment()
        val parametros = Bundle()
        parametros.putParcelable(PRODUTO_DETALHE, produto)
        produtoDetalheFragment.arguments = parametros
        fragmentTransaction.replace(R.id.frame_produto, produtoDetalheFragment)
        fragmentTransaction.commit()
    }

    private fun iniciaProdutoListaFragment(
        fragmentTransaction: FragmentTransaction,
        descricaoCategoria: String
    ) {
        val produtoFragment = ProdutoFragment()
        val parametros = Bundle()
        parametros.putSerializable(DESCRICAO_CATEGORIA, descricaoCategoria)
        produtoFragment.arguments = parametros
        fragmentTransaction.replace(R.id.frame_produto, produtoFragment)
        fragmentTransaction.commit()
    }
}
