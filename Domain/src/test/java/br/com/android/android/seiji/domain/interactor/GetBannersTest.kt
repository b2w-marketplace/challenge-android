package br.com.android.android.seiji.domain.interactor

import br.com.android.seiji.domain.executor.PostExecutionThread
import br.com.android.seiji.domain.interactor.browse.GetBanners
import br.com.android.seiji.domain.model.Banner
import br.com.android.seiji.domain.repository.BannerRepository
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import test.BannerFactory

class GetBannersTest {

    private lateinit var getBanners: GetBanners

    @Mock
    lateinit var bannerRepository: BannerRepository

    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getBanners = GetBanners(bannerRepository, postExecutionThread)
    }

    @Test
    fun getBannersCompletes() {
        stubGetBanners(Observable.just(BannerFactory.makeBannerList(3)))
        val testObserver = getBanners.buildUseCaseObservable().test()
        testObserver.assertComplete()
    }

    @Test
    fun getBannersReturnsData() {
        val bannersList = BannerFactory.makeBannerList(3)
        stubGetBanners(Observable.just(bannersList))
        val testObserver = getBanners.buildUseCaseObservable().test()
        testObserver.assertValue(bannersList)
    }

    private fun stubGetBanners(observable: Observable<List<Banner>>) {
        whenever(bannerRepository.getBanners())
            .thenReturn(observable)
    }

}