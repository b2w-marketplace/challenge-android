package br.com.amedigital.challenge_android.view.ui.categoria

import android.os.Bundle
import android.view.MenuItem
import android.view.View.GONE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.amedigital.challenge_android.R
import br.com.amedigital.challenge_android.extension.observeLiveData
import br.com.amedigital.challenge_android.factory.AppViewModelFactory
import br.com.amedigital.challenge_android.models.Resource
import br.com.amedigital.challenge_android.models.Status
import br.com.amedigital.challenge_android.models.entity.Produto
import br.com.amedigital.challenge_android.view.adapter.CategoriaActivityProdutoListAdapter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.content_categoria.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class CategoriaActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory
    private lateinit var viewModel: CategoriaActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categoria)

        val categoriaId = intent.getIntExtra("categoriaId", 1) as? Int
        val categoriaNome = intent.getStringExtra("categoriaNome")

        setupNavigation(categoriaNome)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CategoriaActivityViewModel::class.java)
        observeLiveData(viewModel.getProdutoListObservable()) { updateProdutoList(it) }
        viewModel.postProdutoPage(categoriaId ?:1)
    }

    private fun updateProdutoList(resource: Resource<List<Produto>>) {
        when (resource.status) {
            Status.LOADING -> Unit
            Status.SUCCESS -> initializeUI(resource)
            Status.ERROR -> toast(resource.errorEnvelope?.status_message.toString())
        }
    }

    private fun initializeUI(resource: Resource<List<Produto>>) {
        categoriaListActivity.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = CategoriaActivityProdutoListAdapter(ArrayList(resource.data))
        }
        
    }

    private fun setupNavigation(categoriaNome: String?) {
        toolbarLogo.visibility = GONE
        toolbarTitle.text = categoriaNome
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

}