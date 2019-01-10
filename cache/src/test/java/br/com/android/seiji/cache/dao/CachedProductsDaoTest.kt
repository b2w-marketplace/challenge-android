package br.com.android.seiji.cache.dao

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.room.Room
import br.com.android.seiji.cache.db.CacheDatabase
import br.com.android.seiji.cache.test.factory.ProductDataFactory
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class CachedProductsDaoTest {

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
    fun getProductsReturnsData() {
        val product = ProductDataFactory.makeCachedProduct()
        database.cachedProductDao().insertProducts(listOf(product))

        val testObserver = database.cachedProductDao().getProducts().test()
        testObserver.assertValue(listOf(product))
    }

    @Test
    fun deleteProductsClearsData() {
        val product = ProductDataFactory.makeCachedProduct()
        database.cachedProductDao().insertProducts(listOf(product))
        database.cachedProductDao().deleteProducts()

        val testObserver = database.cachedProductDao().getProducts().test()
        testObserver.assertValue(emptyList())
    }
}