package br.com.rbueno.lodjinha.ui.home.category

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ViewFlipper
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import br.com.rbueno.lodjinha.R
import br.com.rbueno.lodjinha.model.Category
import br.com.rbueno.lodjinha.model.CategoryItem
import br.com.rbueno.lodjinha.ui.home.TOOLBAR_TITLE_ARG
import br.com.rbueno.lodjinha.util.observe
import br.com.rbueno.lodjinha.viewmodel.HomeViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

const val CATEGORY_ID_ARG = "category_id"

private const val CATEGORY_FLIPPER_POSITION = 0
private const val LOADING_FLIPPER_POSITION = 1
private const val ERROR_FLIPPER_POSITION = 2

class CategoryFragment : Fragment() {

    @Inject
    lateinit var factory: HomeViewModel.HomeViewModelFactory

    private val viewModel by lazy { ViewModelProviders.of(this, factory).get(HomeViewModel::class.java) }

    private val recyclerView by lazy { view?.findViewById<RecyclerView>(R.id.recycler_category) }
    private val buttonTryAgain by lazy { view?.findViewById<Button>(R.id.button_try_again) }

    private lateinit var category: Category

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        configTryAgainButton()
    }

    private fun configTryAgainButton() {
        buttonTryAgain?.setOnClickListener {
            viewModel.loadCategory()
        }
    }

    private fun initViewModel() {
        viewModel.apply {
            errorLiveData.observe(this@CategoryFragment) {
                if (view is ViewFlipper) {
                    (view as ViewFlipper).displayedChild = ERROR_FLIPPER_POSITION
                }
            }

            loadingLiveData.observe(this@CategoryFragment) {
                if (view is ViewFlipper) {
                    (view as ViewFlipper).displayedChild =
                        if (it) LOADING_FLIPPER_POSITION else CATEGORY_FLIPPER_POSITION
                }
            }

            categoryLiveData.observe(this@CategoryFragment) {
                if (view is ViewFlipper) {
                    (view as ViewFlipper).displayedChild = CATEGORY_FLIPPER_POSITION
                }
                category = it
                configRecyclerView(it)
                this.clearCategoryLiveData()
            }
        }.loadCategory()
    }

    private fun configRecyclerView(category: Category) {
        recyclerView?.adapter = CategoryAdapter(category) {
            navigateToProductList(it)
        }
        recyclerView?.adapter?.notifyDataSetChanged()
    }

    private fun navigateToProductList(categoryItem: CategoryItem) {
        findNavController().navigate(R.id.product_list_dest,
            Bundle().apply {
                putString(TOOLBAR_TITLE_ARG, categoryItem.description)
                putInt(CATEGORY_ID_ARG, categoryItem.id)
            })
    }
}