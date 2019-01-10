package br.com.android.seiji.cache

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.room.Room
import br.com.android.seiji.cache.db.CacheDatabase
import br.com.android.seiji.cache.mapper.CachedBannerMapper
import br.com.android.seiji.cache.test.factory.BannerDataFactory
import br.com.android.seiji.cache.test.factory.DataFactory
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class BannersCacheImplTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val database = Room.inMemoryDatabaseBuilder(
        RuntimeEnvironment.application.applicationContext,
        CacheDatabase::class.java
    ).allowMainThreadQueries().build()

    private val entityMapper = CachedBannerMapper()
    private val cache = BannersCacheImpl(database, entityMapper)

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun clearBannersCompletes() {
        val testObserver = cache.clearBanners().test()
        testObserver.assertComplete()
    }

    @Test
    fun saveBannersCompletes() {
        val banners = listOf(BannerDataFactory.makeBannerEntity())

        val testObserver = cache.saveBanners(banners).test()
        testObserver.assertComplete()
    }

    @Test
    fun getBannersReturnsData() {
        val banners = listOf(BannerDataFactory.makeBannerEntity())
        cache.saveBanners(banners).test()

        val testObserver = cache.getBanners().test()
        testObserver.assertValue(banners)
    }

    @Test
    fun areBannersCacheReturnsData() {
        val banners = listOf(BannerDataFactory.makeBannerEntity())
        cache.saveBanners(banners)

        val testObserver = cache.areBannersCached().test()
        testObserver.assertValue(true)
    }

    @Test
    fun setLastCacheTimeCompletes() {
        val testObserver = cache.setLastCacheTime(DataFactory.randomLong()).test()
        testObserver.assertComplete()
    }

    @Test
    fun isBannersCacheExpiredReturnsNotExpired() {
        cache.setLastCacheTime(System.currentTimeMillis() - 1000).test()
        val testObserver = cache.isBannersCacheExpired().test()
        testObserver.assertValue(false)
    }
}