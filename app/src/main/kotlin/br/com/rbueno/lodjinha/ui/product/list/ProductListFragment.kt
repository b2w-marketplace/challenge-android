package br.com.rbueno.lodjinha.ui.product.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.rbueno.lodjinha.R

class ProductListFragment : Fragment() {

    private val recyclerProducts by lazy { view?.findViewById<RecyclerView>(R.id.recycler_products) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_product_list, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupRecyclerView()
        initViewModel()
    }

    private fun initViewModel() {

    }

    private fun setupRecyclerView() {
        with(recyclerProducts) {
            this?.layoutManager = LinearLayoutManager(this@ProductListFragment.context)
            this?.addItemDecoration(
                DividerItemDecoration(
                    this@ProductListFragment.context,
                    LinearLayoutManager.VERTICAL
                )
            )
        }
    }
}