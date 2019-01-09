package br.com.android.seiji.data.store.product

import br.com.android.seiji.data.repository.product.ProductsDataStore
import javax.inject.Inject

class ProductsDataStoreFactory @Inject constructor(
    private val productsCacheDataStore: ProductsCacheDataStore,
    private val productsRemoteDataStore: ProductsRemoteDataStore
) {

    open fun getDataStore(isCached: Boolean, cacheExpired: Boolean): ProductsDataStore {
        return if (isCached && !cacheExpired) {
            productsCacheDataStore
        } else {
            productsRemoteDataStore
        }
    }

    open fun getCacheDataStore(): ProductsDataStore {
        return productsCacheDataStore
    }

    open fun getRemoteDataStore(): ProductsDataStore {
        return productsRemoteDataStore
    }
}