package br.com.android.seiji.remote.mapper

import br.com.android.seiji.remote.test.factory.BannerFactory
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class BannerResponseModelMapperTest {

    private val mapper = BannersResponseModelMapper()

    @Test
    fun mapFromModelMapsData() {
        val model = BannerFactory.makeBannerModel()
        val entity = mapper.mapFromModel(model)

        assertEquals(model.id, entity.id)
        assertEquals(model.linkUrl, entity.linkUrl)
        assertEquals(model.urlImagem, entity.urlImagem)
    }
}