package br.com.android.seiji.data.mapper

import br.com.android.seiji.data.model.CategoryEntity
import br.com.android.seiji.data.test.factory.CategoryFactory
import br.com.android.seiji.domain.model.Category
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class CategoryMapperTest {

    private val mapper = CategoryMapper()

    @Test
    fun mapFromEntityMapsData() {
        val entity = CategoryFactory.makeCategoryEntity()
        val model = mapper.mapFromEntity(entity)

        assertEqualData(entity, model)
    }

    @Test
    fun mapToEntityMapsData() {
        val model = CategoryFactory.makeCategory()
        val entity = mapper.mapToEntity(model)

        assertEqualData(entity, model)
    }

    private fun assertEqualData(entity: CategoryEntity, model: Category) {
        assertEquals(entity.id, model.id)
        assertEquals(entity.descricao, model.descricao)
        assertEquals(entity.urlImagem, model.urlImagem)
    }
}