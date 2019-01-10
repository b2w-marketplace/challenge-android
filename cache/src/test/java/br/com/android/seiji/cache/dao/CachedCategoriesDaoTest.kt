package br.com.android.seiji.cache.dao

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.room.Room
import br.com.android.seiji.cache.db.CacheDatabase
import br.com.android.seiji.cache.test.factory.CategoryDataFactory
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class CachedCategoriesDaoTest {

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
    fun getCategoriesReturnsData() {
        val category = CategoryDataFactory.makeCachedCategory()
        database.cachedCategoryDao().insertCategories(listOf(category))

        val testObserver = database.cachedCategoryDao().getCategories().test()
        testObserver.assertValue(listOf(category))
    }

    @Test
    fun deleteCategoriesClearsData() {

        val category = CategoryDataFactory.makeCachedCategory()

        database.cachedCategoryDao().insertCategories(listOf(category))
        database.cachedCategoryDao().deleteCategories()

        val testObserver = database.cachedCategoryDao().getCategories().test()

        testObserver.assertValue(emptyList())
    }
}
