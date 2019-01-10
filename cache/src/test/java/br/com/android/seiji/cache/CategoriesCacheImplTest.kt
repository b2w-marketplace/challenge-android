package br.com.android.seiji.cache

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.room.Room
import br.com.android.seiji.cache.db.CacheDatabase
import br.com.android.seiji.cache.mapper.CachedCategoryMapper
import br.com.android.seiji.cache.test.factory.CategoryDataFactory
import br.com.android.seiji.cache.test.factory.DataFactory
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment


@RunWith(RobolectricTestRunner::class)
class CategoriesCacheImplTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val database = Room.inMemoryDatabaseBuilder(
            RuntimeEnvironment.application.applicationContext,
            CacheDatabase::class.java
    ).allowMainThreadQueries().build()

    private val entityMapper = CachedCategoryMapper()
    private val cache = CategoriesCacheImpl(database, entityMapper)

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun clearCategoriesCompletes() {
        val testObserver = cache.clearCategories().test()
        testObserver.assertComplete()
    }

    @Test
    fun saveCategoriesCompletes() {
        val categories = listOf(CategoryDataFactory.makeCategoryEntity())
        val testObserver = cache.saveCategories(categories).test()
        testObserver.assertComplete()
    }

    @Test
    fun getCategoriesReturnsData() {
        val categories = listOf(CategoryDataFactory.makeCategoryEntity())
        cache.saveCategories(categories).test()

        val testObserver = cache.getCategories().test()
        testObserver.assertValue(categories)
    }

    @Test
    fun areCategoriesCacheReturnsData() {
        val categories = listOf(CategoryDataFactory.makeCategoryEntity())
        cache.saveCategories(categories)

        val testObserver = cache.areCategoriesCached().test()
        testObserver.assertValue(true)
    }

    @Test
    fun setLastCacheTimeCompletes() {
        val testObserver = cache.setLastCacheTime(DataFactory.randomLong()).test()
        testObserver.assertComplete()
    }

    @Test
    fun isCategoriesCacheExpiredReturnsNotExpired() {

        cache.setLastCacheTime(System.currentTimeMillis() - 1000).test()
        val testObserver = cache.isCacheExpired().test()
        testObserver.assertValue(false)
    }
}
