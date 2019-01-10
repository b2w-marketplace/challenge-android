package br.com.android.seiji.remote.mapper

import br.com.android.seiji.data.model.CategoryEntity
import br.com.android.seiji.remote.model.CategoryModel
import javax.inject.Inject

class CategoriesResponseModelMapper @Inject constructor() : ModelMapper<CategoryModel, CategoryEntity> {

    override fun mapFromModel(model: CategoryModel): CategoryEntity {
        return CategoryEntity(
            model.id, model.descricao, model.urlImagem
        )
    }
}