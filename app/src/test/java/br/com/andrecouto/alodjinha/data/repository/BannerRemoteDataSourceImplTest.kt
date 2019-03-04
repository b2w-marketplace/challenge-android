package br.com.andrecouto.alodjinha.data.repository

import br.com.andrecouto.alodjinha.data.source.remote.banner.BannerRemoteDataSource
import br.com.andrecouto.alodjinha.data.source.remote.repository.BannerRepositoryImpl
import br.com.andrecouto.alodjinha.domain.factory.banner.BannerFactory
import br.com.andrecouto.alodjinha.domain.model.lodjinha.Banner
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Test

class BannerRemoteDataSourceImplTest {

    private val bannerRemoteDataSource = mock<BannerRemoteDataSource>()
    private val repository = BannerRepositoryImpl(bannerRemoteDataSource)

    @Test
    fun getBannersCompletes() {
        stubGetBanners(Single.just(BannerFactory.makeBannerList(3)))
        val testObserver = repository.getBanners().test()
        testObserver.assertComplete()
    }

    @Test
    fun getBannersReturnData() {
        val banners = BannerFactory.makeBannerList(3)
        stubGetBanners(Single.just(banners))
        val testObserver = repository.getBanners().test()
        testObserver.assertValue(banners)
    }

    private fun stubGetBanners(single: Single<List<Banner>>) {
        whenever(bannerRemoteDataSource.getBanners())
                .thenReturn(single)
    }
}