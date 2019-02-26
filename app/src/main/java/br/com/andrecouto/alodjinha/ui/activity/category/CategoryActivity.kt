package br.com.andrecouto.alodjinha.ui.activity.category

import android.os.Bundle
import br.com.andrecouto.alodjinha.databinding.ActivityCategoryBinding
import br.com.andrecouto.alodjinha.ui.base.BaseActivity
import br.com.andrecouto.alodjinha.R
import br.com.andrecouto.alodjinha.ui.fragment.category.CategoryFragment
import br.com.andrecouto.alodjinha.ui.fragment.category.CategoryViewModel
import br.com.andrecouto.alodjinha.util.FragmentUtil

class CategoryActivity : BaseActivity<CategoryViewModel, ActivityCategoryBinding>() {

    override val layoutId: Int = R.layout.activity_category
    override val viewModel: CategoryViewModel by getLazyViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        FragmentUtil.replaceFragment(supportFragmentManager, CategoryFragment.newInstance(), R.id.frame_category_container, false)

    }
}
