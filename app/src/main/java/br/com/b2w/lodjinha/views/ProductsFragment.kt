package br.com.b2w.lodjinha.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.b2w.lodjinha.R
import kotlinx.android.synthetic.main.fragment_products.*
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductsFragment : BaseFragment() {

    private val viewModel: ProductsViewModel by viewModel()
    private val adapter: ProductsAdapter by inject()
    private val args: ProductsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_products, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        getProducts()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        adapter.clear()
    }

    private fun setupRecyclerView() {
        with(productsRecyclerView) {
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = this@ProductsFragment.adapter
        }
    }

    private fun getProducts() {
        adapter.onProductSelected { product ->
            val action =
                ProductsFragmentDirections.actionProductsFragmentToProductDetailsFragment(
                    args.category.description,
                    product.id
                )
            findNavController().navigate(action)
        }

        launch {
            viewModel.getProducts(args.category.id).observe(this@ProductsFragment, Observer {
                productsProgressBar.visibility = View.INVISIBLE
                adapter.submitList(it)
            })
            viewModel.loadingState.observe(this@ProductsFragment, Observer { state ->
                adapter.updateLoadingState(state)
            })
        }
    }
}