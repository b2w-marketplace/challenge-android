package br.com.android.seiji.data.store

import br.com.android.seiji.data.model.BannerEntity
import br.com.android.seiji.data.repository.BannersRemote
import br.com.android.seiji.data.test.factory.BannerFactory
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Flowable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class BannersRemoteDataStoreTest {

    private val remote = mock<BannersRemote>()
    private val store = BannerRemoteDataStore(remote)

    @Test
    fun getBannersCompletes() {
        stubRemoteGetBanners(Flowable.just(listOf(BannerFactory.makeBannerEntity())))
        val testObserver = store.getBanners().test()
        testObserver.assertComplete()
    }

    @Test
    fun getBannersReturnsData() {
        val data = listOf(BannerFactory.makeBannerEntity())
        stubRemoteGetBanners(Flowable.just(data))
        val testObserver = store.getBanners().test()
        testObserver.assertValue(data)
    }

    @Test(expected = UnsupportedOperationException::class)
    fun clearBannersThrowsException() {
        store.cleanBanners().test()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun saveBannersThrowsException() {
        store.saveBanners(listOf(BannerFactory.makeBannerEntity())).test()
    }


    private fun stubRemoteGetBanners(observable: Flowable<List<BannerEntity>>) {
        whenever(store.getBanners())
            .thenReturn(observable)
    }
}