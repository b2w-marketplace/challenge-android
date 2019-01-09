package br.com.android.seiji.data.store

import br.com.android.seiji.data.store.banners.BannersCacheDataStore
import br.com.android.seiji.data.store.banners.BannersDataStoreFactory
import br.com.android.seiji.data.store.banners.BannersRemoteDataStore
import com.nhaarman.mockito_kotlin.mock
import org.junit.Test
import kotlin.test.assertEquals

class BannersDataStoreFactoryTest {

    private val cacheStore = mock<BannersCacheDataStore>()
    private val remoteStore = mock<BannersRemoteDataStore>()
    private val factory = BannersDataStoreFactory(cacheStore, remoteStore)

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