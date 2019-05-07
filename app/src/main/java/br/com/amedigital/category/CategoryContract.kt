package br.com.amedigital.category

import br.com.amedigital.model.Category

interface CategoryContract {
    interface View {
        fun setProgressIndicatorCategory(active: Boolean)

        fun showCategories(categories: ArrayList<Category>)

        fun showErrorCategory(message : String)
    }

    interface CategoryActionListener {
        fun loadCategories()
    }
}