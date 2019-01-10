package br.com.android.seiji.data.store.products

import br.com.android.seiji.data.store.product.ProductsCacheDataStore
import br.com.android.seiji.data.store.product.ProductsDataStoreFactory
import br.com.android.seiji.data.store.product.ProductsRemoteDataStore
import com.nhaarman.mockito_kotlin.mock
import org.junit.Test
import kotlin.test.assertEquals

class ProductsDataStoreFactoryTest {

    private val cacheStore = mock<ProductsCacheDataStore>()
    private val remoteStore = mock<ProductsRemoteDataStore>()
    private val factory = ProductsDataStoreFactory(cacheStore, remoteStore)

    @Test
    fun getDataStoreReturnsRemoteStoreWhenCacheExpired() {
        assertEquals(remoteStore, factory.getDataStore(true, true))
    }

    @Test
    fun getDataStoreReturnsRemoteStoreWhenBannersNotCached() {
        assertEquals(remoteStore, factory.getDataStore(false, false))
    }

    @Test
    fun getDataStoreReturnsCacheStore() {
        assertEquals(cacheStore, factory.getDataStore(true, false))
    }

    @Test
    fun getCacheDataStoreReturnsCacheStore() {
        assertEquals(cacheStore, factory.getCacheDataStore())
    }
}