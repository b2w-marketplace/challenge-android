package com.sumiya.olodjinha.ui.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sumiya.olodjinha.R
import com.sumiya.olodjinha.contracts.ViewCategoryFragmentContract
import com.sumiya.olodjinha.model.CategoryDataModel
import com.sumiya.olodjinha.model.CategoryModel
import com.sumiya.olodjinha.presenter.CategoryPresenter
import com.sumiya.olodjinha.ui.adapter.CategoryAdapter
import kotlinx.android.synthetic.main.fragment_category.*

class CategoryFragment : Fragment(), ViewCategoryFragmentContract {
    //Variables
    private var listener: CategoryListener? = null

    //Lifecycle
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is CategoryListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement CategoryListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val categoryPresenter = CategoryPresenter(this)
        categoryPresenter.getCategories()
    }

    //Actions
    private fun categoryClicked(category: CategoryModel) {
        listener!!.requestProductList(category)
    }

    //Contract
    override fun setCategoryResponse(categories: CategoryDataModel) {
        categoriesRecycler.adapter = CategoryAdapter(
                categories
        ) { category: CategoryModel -> categoryClicked(category) }

        categoriesRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    //Listener Interface
    interface CategoryListener {
        fun requestProductList(category: CategoryModel)
    }

}
