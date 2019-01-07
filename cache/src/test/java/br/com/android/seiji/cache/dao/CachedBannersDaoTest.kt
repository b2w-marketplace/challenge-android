package br.com.android.seiji.cache.dao

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.room.Room
import br.com.android.seiji.cache.db.CacheDatabase
import br.com.android.seiji.cache.test.factory.BannerDataFactory
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class CachedBannersDaoTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val database = Room.inMemoryDatabaseBuilder(
        RuntimeEnvironment.application.applicationContext,
        CacheDatabase::class.java
    )
        .allowMainThreadQueries()
        .build()

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun getBannersReturnsData() {
        val banner = BannerDataFactory.makeCachedBanner()
        database.cachedBannerDao().insertBanners(listOf(banner))

        val testObserver = database.cachedBannerDao().getBanners().test()
        testObserver.assertValue(listOf(banner))
    }

    @Test
    fun deleteBannersClearsData() {
        val banner = BannerDataFactory.makeCachedBanner()
        database.cachedBannerDao().insertBanners(listOf(banner))
        database.cachedBannerDao().deleteBanners()

        val testObserver = database.cachedBannerDao().getBanners().test()
        testObserver.assertValue(emptyList())
    }
}