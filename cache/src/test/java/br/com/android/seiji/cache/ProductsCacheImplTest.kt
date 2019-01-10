package br.com.android.seiji.cache

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.room.Room
import br.com.android.seiji.cache.db.CacheDatabase
import br.com.android.seiji.cache.mapper.CachedProductMapper
import br.com.android.seiji.cache.test.factory.DataFactory
import br.com.android.seiji.cache.test.factory.ProductDataFactory
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class ProductsCacheImplTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val database = Room.inMemoryDatabaseBuilder(
            RuntimeEnvironment.application.applicationContext,
            CacheDatabase::class.java
    ).allowMainThreadQueries().build()

    private val entityMapper = CachedProductMapper()
    private val cache = ProductsCacheImpl(database, entityMapper)

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun clearBestSellerProductsCompletes() {
        val testObserver = cache.clearBestSellerProducts().test()
        testObserver.assertComplete()
    }

    @Test
    fun saveBestSellerProductsCompletes() {
        val products = listOf(ProductDataFactory.makeProductEntity())
        val testObserver = cache.saveBestSellerProducts(products).test()
        testObserver.assertComplete()
    }

    @Test
    fun getBestSellerProductsReturnsData() {
        val products = listOf(ProductDataFactory.makeProductEntity())
        cache.saveBestSellerProducts(products).test()

        val testObserver = cache.getBestSellerProducts().test()
        testObserver.assertValue(products)
    }

    @Test
    fun areProductsCacheReturnsData() {
        val products = listOf(ProductDataFactory.makeProductEntity())
        cache.saveBestSellerProducts(products)

        val testObserver = cache.areProductsCached().test()
        testObserver.assertValue(true)
    }

    @Test
    fun setLastCacheTimeCompletes() {
        val testObserver = cache.setLastCacheTime(DataFactory.randomLong()).test()
        testObserver.assertComplete()
    }

    @Test
    fun isProductsCacheExpiredReturnsNotExpired() {
        cache.setLastCacheTime(System.currentTimeMillis() - 1000).test()
        val testObserver = cache.isCacheExpired().test()
        testObserver.assertValue(false)
    }
}