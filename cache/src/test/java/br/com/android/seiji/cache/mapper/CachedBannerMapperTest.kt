package br.com.android.seiji.cache.mapper

import br.com.android.seiji.cache.model.CachedBanner
import br.com.android.seiji.cache.test.factory.BannerDataFactory
import br.com.android.seiji.data.model.BannerEntity
import org.junit.Test
import kotlin.test.assertEquals

class CachedBannerMapperTest {

    private val mapper = CachedBannerMapper()

    @Test
    fun mapFromCachedMapsData() {
        val model = BannerDataFactory.makeCachedBanner()
        val entity = mapper.mapFromCached(model)

        assertEqualsData(model, entity)
    }

    @Test
    fun mapToCachedMapsData() {
        val entity = BannerDataFactory.makeBannerEntity()
        val model = mapper.mapToCached(entity)

        assertEqualsData(model, entity)
    }

    private fun assertEqualsData(model: CachedBanner, entity: BannerEntity) {
        assertEquals(model.id, entity.id)
        assertEquals(model.linkUrl, entity.linkUrl)
        assertEquals(model.urlImagem, entity.urlImagem)
    }

}