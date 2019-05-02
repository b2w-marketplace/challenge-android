package br.com.amedigital.challenge_android.view.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import br.com.amedigital.challenge_android.R
import br.com.amedigital.challenge_android.extension.observeLiveData
import br.com.amedigital.challenge_android.factory.AppViewModelFactory
import br.com.amedigital.challenge_android.models.Resource
import br.com.amedigital.challenge_android.models.Status
import br.com.amedigital.challenge_android.models.entity.Categoria
import br.com.amedigital.challenge_android.view.adapter.CategoriaListAdapter
import br.com.amedigital.challenge_android.view.viewholder.CategoriaListViewHolder
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_categoria_list.*
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

class CategoriaListFragment : Fragment(), CategoriaListViewHolder.Delegate {

    @Inject
    lateinit var viewModelFactory: AppViewModelFactory
    private lateinit var viewModel: HomeActivityViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_categoria_list, container, false)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeActivityViewModel::class.java)
        observeLiveData(viewModel.getCategoriaListObservable()) { updateBannerList(it) }
        viewModel.postCategoriaPage(1)
    }

    private fun updateBannerList(resource: Resource<List<Categoria>>) {
        when (resource.status) {
            Status.LOADING -> Unit
            Status.SUCCESS -> initializeUI(resource)
            Status.ERROR -> toast(resource.errorEnvelope?.status_message.toString())
        }
    }

    private fun initializeUI(resource: Resource<List<Categoria>>){
        categoriaList.apply {
            layoutManager = LinearLayoutManager(activity, OrientationHelper.HORIZONTAL, false)
            adapter = CategoriaListAdapter(ArrayList(resource.data))
        }
    }

    override fun onItemClick(categoria: Categoria) {}

}