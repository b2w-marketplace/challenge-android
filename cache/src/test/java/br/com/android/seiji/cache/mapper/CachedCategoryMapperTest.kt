package br.com.android.seiji.cache.mapper

import br.com.android.seiji.cache.model.CachedCategory
import br.com.android.seiji.cache.test.factory.CategoryDataFactory
import br.com.android.seiji.data.model.CategoryEntity
import org.junit.Test
import kotlin.test.assertEquals

class CachedCategoryMapperTest {

    private val mapper = CachedCategoryMapper()

    @Test
    fun mapFromCachedMapsData() {
        val model = CategoryDataFactory.makeCachedCategory()
        val entity = mapper.mapFromCached(model)

        assertEqualsData(model, entity)
    }

    @Test
    fun mapToCachedMapsData() {
        val entity = CategoryDataFactory.makeCategoryEntity()
        val model = mapper.mapToCached(entity)

        assertEqualsData(model, entity)
    }

    private fun assertEqualsData(model: CachedCategory, entity: CategoryEntity) {
        assertEquals(model.id, entity.id)
        assertEquals(model.categoriaDescricao, entity.descricao)
        assertEquals(model.categoriaUrlImagem, entity.urlImagem)
    }
}


