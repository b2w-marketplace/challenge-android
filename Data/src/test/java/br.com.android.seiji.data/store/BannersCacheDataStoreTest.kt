package br.com.android.seiji.data.store

import br.com.android.seiji.data.model.BannerEntity
import br.com.android.seiji.data.repository.BannersCache
import br.com.android.seiji.data.test.factory.BannerFactory
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.Flowable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class BannersCacheDataStoreTest {

    private val cache = mock<BannersCache>()
    private val store = BannerCacheDataStore(cache)

    @Test
    fun getBannersCompletes() {
        stubBannersCacheGetBanners(Flowable.just(listOf(BannerFactory.makeBannerEntity())))

        val testObserver = store.getBanners().test()
        testObserver.assertComplete()
    }

    @Test
    fun getBannersReturnsData() {
        val data = listOf(BannerFactory.makeBannerEntity())
        stubBannersCacheGetBanners(Flowable.just(data))

        val testObserver = store.getBanners().test()
        testObserver.assertValue(data)
    }

    @Test
    fun getBannersCallsCacheSource() {
        stubBannersCacheGetBanners(Flowable.just(listOf(BannerFactory.makeBannerEntity())))

        store.getBanners().test()
        verify(cache).getBanners()
    }

    @Test
    fun saveBannersCompletes() {
        stubBannersCacheSaveBanners(Completable.complete())
        stubBannersCacheSetLastCacheTime(Completable.complete())

        val testObserver = store.saveBanners(listOf(BannerFactory.makeBannerEntity())).test()
        testObserver.assertComplete()
    }

    @Test
    fun saveBannersCallsCacheStore(){
        val data = listOf(BannerFactory.makeBannerEntity())

        stubBannersCacheSaveBanners(Completable.complete())
        stubBannersCacheSetLastCacheTime(Completable.complete())

        store.saveBanners(data).test()
        verify(cache).saveBanners(data)
    }

    @Test
    fun cleanBannersCompletes(){
        stubBannersClearBanners(Completable.complete())

        val testObserver = store.cleanBanners().test()
        testObserver.assertComplete()
    }

    @Test
    fun clearBannersCallsCacheStore(){
        stubBannersClearBanners(Completable.complete())

        store.cleanBanners().test()
        verify(cache).cleanBanners()
    }

    private fun stubBannersCacheGetBanners(observable: Flowable<List<BannerEntity>>) {
        whenever(cache.getBanners())
            .thenReturn(observable)
    }

    private fun stubBannersCacheSaveBanners(completable: Completable) {
        whenever(cache.saveBanners(any()))
            .thenReturn(completable)
    }

    private fun stubBannersCacheSetLastCacheTime(completable: Completable) {
        whenever(cache.setLastCacheTime(any()))
            .thenReturn(completable)
    }

    private fun stubBannersClearBanners(completable: Completable) {
        whenever(cache.cleanBanners())
            .thenReturn(completable)
    }
}