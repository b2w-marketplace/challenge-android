package br.com.android.seiji.mobileui.mapper

import br.com.android.seiji.domain.model.Category
import br.com.android.seiji.presentation.model.CategoryView
import javax.inject.Inject

class CategoryViewMapper @Inject constructor() : ViewMapper<CategoryView, Category> {
    override fun mapToView(presentation: CategoryView): Category {
        return Category(
                presentation.id, presentation.descricao, presentation.urlImagem
        )
    }
}