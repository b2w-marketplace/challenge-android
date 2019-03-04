package br.com.andrecouto.alodjinha.domain.interactor.banner

import br.com.andrecouto.alodjinha.domain.factory.banner.BannerFactory
import br.com.andrecouto.alodjinha.domain.mapper.DomainErrorUtil
import br.com.andrecouto.alodjinha.domain.model.lodjinha.Banner
import br.com.andrecouto.alodjinha.domain.repository.BannerRepository
import br.com.andrecouto.alodjinha.domain.usecase.banner.GetBanners
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetBannersUseCaseTest {

    private lateinit var getBannersUseCase: GetBanners
    @Mock
    lateinit var bannerRepository: BannerRepository
    @Mock
    lateinit var domainErrorUtil: DomainErrorUtil

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getBannersUseCase = GetBanners(domainErrorUtil, bannerRepository)
    }

    @Test
    fun getBannersCompletes() {
        stubBanners(Single.just(BannerFactory.makeBannerList(3)))
        val testSingle = getBannersUseCase.buildUseCaseObservable().test()
        testSingle.assertComplete()
    }

    @Test
    fun getBannersReturnData() {
        val banners = BannerFactory.makeBannerList(3)
        stubBanners(Single.just(banners))
        val testSingle = getBannersUseCase.buildUseCaseObservable().test()
        testSingle.assertValue(banners)
    }

    private fun stubBanners(observable: Single<List<Banner>>) {
        whenever(bannerRepository.getBanners())
                .thenReturn(observable)
    }
}