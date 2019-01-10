package br.com.android.seiji.presentation.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.android.seiji.domain.interactor.banner.GetBanners
import br.com.android.seiji.domain.model.Banner
import br.com.android.seiji.presentation.viewModel.GetBannersViewModel
import br.com.android.seiji.presentation.mapper.BannerViewMapper
import br.com.android.seiji.presentation.model.BannerView
import br.com.android.seiji.presentation.state.ResourceState
import br.com.android.seiji.presentation.test.factory.BannerFactory
import br.com.android.seiji.presentation.test.factory.DataFactory
import com.nhaarman.mockito_kotlin.*
import io.reactivex.observers.DisposableObserver
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Captor

@RunWith(JUnit4::class)
class GetBannersViewModelTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    var getBanners = mock<GetBanners>()
    var bannerViewMapper = mock<BannerViewMapper>()
    var getBannersViewModel = GetBannersViewModel(
            getBanners, bannerViewMapper
    )

    @Captor
    val captor = argumentCaptor<DisposableObserver<List<Banner>>>()

    @Test
    fun fetchBannersExecutesUseCase() {
        getBannersViewModel.fetchBanners()

        verify(getBanners, times(1)).execute(any(), eq(null))
    }

    @Test
    fun fetchBannersReturnsSuccess() {
        val banners = BannerFactory.makeBannerList(3)
        val bannerViews = BannerFactory.makeBannerViewList(3)

        stubBannerMapperMapToView(bannerViews[0], banners[0])
        stubBannerMapperMapToView(bannerViews[1], banners[1])
        stubBannerMapperMapToView(bannerViews[2], banners[2])

        getBannersViewModel.fetchBanners()

        verify(getBanners).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(banners)

        assertEquals(
            ResourceState.SUCCESS,
            getBannersViewModel.getBanners().value?.status
        )
    }

    @Test
    fun fetchBannersReturnsData() {
        val banners = BannerFactory.makeBannerList(3)
        val bannerViews = BannerFactory.makeBannerViewList(3)

        stubBannerMapperMapToView(bannerViews[0], banners[0])
        stubBannerMapperMapToView(bannerViews[1], banners[1])
        stubBannerMapperMapToView(bannerViews[2], banners[2])

        getBannersViewModel.fetchBanners()

        verify(getBanners).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(banners)

        assertEquals(
            bannerViews,
            getBannersViewModel.getBanners().value?.data
        )
    }

    @Test
    fun fetchBannersReturnsError() {
        getBannersViewModel.fetchBanners()

        verify(getBanners).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException())

        assertEquals(
            ResourceState.ERROR,
            getBannersViewModel.getBanners().value?.status
        )
    }

    @Test
    fun fetchBannersReturnsMessageForError() {
        val errorMessage = DataFactory.randomString()
        getBannersViewModel.fetchBanners()

        verify(getBanners).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException(errorMessage))

        assertEquals(errorMessage, getBannersViewModel.getBanners().value?.message)
    }

    private fun stubBannerMapperMapToView(
        bannerView: BannerView,
        banner: Banner
    ) {
        whenever(bannerViewMapper.mapToView(banner))
            .thenReturn(bannerView)
    }
}