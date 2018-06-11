package br.com.andreguedes.alodjinha.ui.main.home

import br.com.andreguedes.alodjinha.data.model.Banner
import br.com.andreguedes.alodjinha.data.model.BannerResponse
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class HomePresenterTest {

    @Mock private lateinit var view : HomeContract.View

    private lateinit var presenter : HomeContract.Presenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        presenter = HomePresenter(view)
    }

    @Test
    fun shouldReturnThreeBannersFromService() {
//        presenter.getBanners()
//
//        val banners = arrayListOf<Banner>(
//                Banner(1, "https://images-submarino.b2w.io/spacey/2017/02/06/MainTop_GAMES_FEV17.png", "https://images-submarino.b2w.io/spacey/2017/02/06/MainTop_GAMES_FEV17.png"),
//                Banner(2, "https://images-submarino.b2w.io/spacey/2017/02/06/DESTAQUE_FULL_CARTAO_CASA_FEV.png", "https://images-submarino.b2w.io/spacey/2017/02/06/DESTAQUE_FULL_CARTAO_CASA_FEV.png"),
//                Banner(3, "https://images-submarino.b2w.io/spacey/2017/02/03/sub-home-dest-full-655x328-touch-play.png", "https://images-submarino.b2w.io/spacey/2017/02/03/sub-home-dest-full-655x328-touch-play.png")
//        )
//        val bannerResponse = BannerResponse(banners)
//
//        Mockito.verify(view).setBanners(bannerResponse.bannerList!!)
    }

}