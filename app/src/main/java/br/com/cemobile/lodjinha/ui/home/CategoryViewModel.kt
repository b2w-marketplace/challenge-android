package br.com.cemobile.lodjinha.ui.home

import br.com.cemobile.domain.model.Category
import br.com.cemobile.lodjinha.ui.base.BaseViewModel

class CategoryViewModel(val category: Category) : BaseViewModel<Category>() {

    val description = category.description
    val imageUrl = category.imageUrl

}