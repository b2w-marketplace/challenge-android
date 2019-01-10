package br.com.android.seiji.presentation.mapper

import br.com.android.seiji.domain.model.Category
import br.com.android.seiji.presentation.model.CategoryView
import javax.inject.Inject

class CategoryViewMapper @Inject constructor() : Mapper<CategoryView, Category> {

    override fun mapToView(type: Category): CategoryView {
        return CategoryView(
                type.id, type.descricao, type.urlImagem
        )
    }
}