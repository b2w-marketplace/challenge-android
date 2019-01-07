package br.com.android.seiji.data.mapper

import br.com.android.seiji.data.model.BannerEntity
import br.com.android.seiji.data.test.factory.BannerFactory
import br.com.android.seiji.domain.model.Banner
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class BannerMapperTest {

    private val mapper = BannerMapper()

    @Test
    fun mapFromEntityMapsData() {
        val entity = BannerFactory.makeBannerEntity()
        val model = mapper.mapFromEntity(entity)

        assertEqualData(entity, model)
    }

    @Test
    fun mapToEntityMapsData() {
        val model = BannerFactory.makeBanner()
        val entity = mapper.mapToEntity(model)

        assertEqualData(entity, model)
    }

    private fun assertEqualData(entity: BannerEntity, model: Banner) {
        assertEquals(entity.id, model.id)
        assertEquals(entity.urlImagem, model.urlImagem)
        assertEquals(entity.linkUrl, model.linkUrl)
    }
}