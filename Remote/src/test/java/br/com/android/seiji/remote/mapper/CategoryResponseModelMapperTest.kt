package br.com.android.seiji.remote.mapper

import br.com.android.seiji.remote.test.factory.CategoryFactory
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class CategoryResponseModelMapperTest {

    private val mapper = CategoriesResponseModelMapper()

    @Test
    fun mapFromModelMapsData() {
        val model = CategoryFactory.makeCategoryModel()
        val entity = mapper.mapFromModel(model)

        assertEquals(model.id, entity.id)
        assertEquals(model.descricao, entity.descricao)
        assertEquals(model.urlImagem, entity.urlImagem)
    }
}