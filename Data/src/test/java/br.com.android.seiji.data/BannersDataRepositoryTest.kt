package br.com.android.seiji.data

import br.com.android.seiji.data.mapper.BannerMapper
import br.com.android.seiji.data.model.BannerEntity
import br.com.android.seiji.data.repository.BannersCache
import br.com.android.seiji.data.repository.BannersDataStore
import br.com.android.seiji.data.store.BannersDataStoreFactory
import br.com.android.seiji.data.test.factory.BannerFactory
import br.com.android.seiji.domain.model.Banner
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class BannersDataRepositoryTest {

    private val mapper = mock<BannerMapper>()
    private val factory = mock<BannersDataStoreFactory>()
    private val store = mock<BannersDataStore>()
    private val cache = mock<BannersCache>()
    private val repository = BannersDataRepository(mapper, cache, factory)

    @Before
    fun setup() {
        stubFactoryGetDataStore()
        stubFactoryGetCacheDataStore()
        stubIsCacheExpired(Single.just(false))
        stubAreBannersCached(Single.just(false))
        stubSaveBanners(Completable.complete())
    }

    @Test
    fun getBannersCompletes() {
        stubGetBanners(Flowable.just(listOf(BannerFactory.makeBannerEntity())))
        stubMapper(BannerFactory.makeBanner(), BannerFactory.makeBannerEntity())

        val testObserver = repository.getBanners().test()
        testObserver.assertComplete()
    }

    @Test
    fun getBannersReturnsData() {
        val entity = BannerFactory.makeBannerEntity()
        val model = BannerFactory.makeBanner()

        stubGetBanners(Flowable.just(listOf(entity)))
        stubMapper(model, entity)

        val testObserver = repository.getBanners().test()
        testObserver.assertValue(listOf(model))
    }

    private fun stubFactoryGetDataStore() {
        whenever(factory.getDataStore(any(), any()))
            .thenReturn(store)
    }

    private fun stubFactoryGetCacheDataStore() {
        whenever(factory.getCacheDataStore())
            .thenReturn(store)
    }

    private fun stubMapper(model: Banner, entity: BannerEntity) {
        whenever(mapper.mapFromEntity(entity))
            .thenReturn(model)
    }

    private fun stubGetBanners(observable: Flowable<List<BannerEntity>>) {
        whenever(store.getBanners())
            .thenReturn(observable)
    }

    private fun stubIsCacheExpired(single: Single<Boolean>) {
        whenever(cache.isBannersCacheExpired())
            .thenReturn(single)
    }

    private fun stubAreBannersCached(single: Single<Boolean>) {
        whenever(cache.areBannersCached())
            .thenReturn(single)
    }

    private fun stubSaveBanners(completable: Completable) {
        whenever(store.saveBanners(any()))
            .thenReturn(completable)
    }
}