package br.com.android.seiji.data.store.categories

import br.com.android.seiji.data.store.category.CategoriesCacheDataStore
import br.com.android.seiji.data.store.category.CategoriesDataStoreFactory
import br.com.android.seiji.data.store.category.CategoriesRemoteDataStore
import com.nhaarman.mockito_kotlin.mock
import org.junit.Test
import kotlin.test.assertEquals

class CategoriesDataStoreFactoryTest {

    private val cacheStore = mock<CategoriesCacheDataStore>()
    private val remoteStore = mock<CategoriesRemoteDataStore>()
    private val factory = CategoriesDataStoreFactory(cacheStore, remoteStore)

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