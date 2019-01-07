package br.com.android.seiji.cache.dao

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.room.Room
import br.com.android.seiji.cache.db.CacheDatabase
import br.com.android.seiji.cache.test.factory.CacheConfigDataFactory
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class CachedConfigDaoTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val database = Room.inMemoryDatabaseBuilder(
        RuntimeEnvironment.application.applicationContext,
        CacheDatabase::class.java
    ).allowMainThreadQueries().build()

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun saveCacheConfigurationSaveData() {
        val configuration = CacheConfigDataFactory.makeCacheConfig()
        database.cacheConfigDao().insertCacheConfig(configuration)

        val testObserver = database.cacheConfigDao().getCacheConfig().test()
        testObserver.assertValue(configuration)
    }
}