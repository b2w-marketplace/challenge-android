package br.com.android.seiji.cache.mapper

import br.com.android.seiji.cache.model.CachedProduct
import br.com.android.seiji.cache.test.factory.ProductDataFactory
import br.com.android.seiji.data.model.ProductEntity
import org.junit.Test
import kotlin.test.assertEquals


class CachedProductMapperTest {

    private val mapper = CachedProductMapper()

    @Test
    fun mapFromCachedMapsData() {
        val model = ProductDataFactory.makeCachedProduct()
        val entity = mapper.mapFromCached(model)
        assertEqualsData(model, entity)
    }

    @Test
    fun mapToCachedMapsData() {
        val entity = ProductDataFactory.makeProductEntity()
        val model = mapper.mapToCached(entity)
        assertEqualsData(model, entity)
    }

    private fun assertEqualsData(model: CachedProduct, entity: ProductEntity) {

        assertEquals(model.id, entity.id)
        assertEquals(model.nome, entity.nome)
        assertEquals(model.descricao, entity.descricao)
        assertEquals(model.precoDe, entity.precoDe)
        assertEquals(model.precoPor, entity.precoPor)
        assertEquals(model.urlImagem, entity.urlImagem)

        assertEquals(model.categoria.id, entity.category.id)
        assertEquals(model.categoria.categoriaDescricao, entity.category.descricao)
        assertEquals(model.categoria.categoriaUrlImagem, entity.category.urlImagem)
    }
}