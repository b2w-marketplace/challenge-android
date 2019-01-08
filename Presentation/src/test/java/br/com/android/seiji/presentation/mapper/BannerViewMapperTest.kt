package br.com.android.seiji.presentation.mapper

import br.com.android.seiji.presentation.test.factory.BannerFactory
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class BannerViewMapperTest {

    private val mapper = BannerViewMapper()

    @Test
    fun mapToViewMapsData() {
        val banner = BannerFactory.makeBanner()
        val bannerView = mapper.mapToView(banner)

        assertEquals(banner.id, bannerView.id)
        assertEquals(banner.linkUrl, bannerView.linkUrl)
        assertEquals(banner.urlImagem, bannerView.urlImagem)
    }
}