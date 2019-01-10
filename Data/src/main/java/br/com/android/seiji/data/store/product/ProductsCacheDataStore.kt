package br.com.android.seiji.data.store.product

import br.com.android.seiji.data.model.ProductEntity
import br.com.android.seiji.data.repository.product.ProductsCache
import br.com.android.seiji.data.repository.product.ProductsDataStore
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class ProductsCacheDataStore @Inject constructor(
    private val productsCache: ProductsCache
) : ProductsDataStore {

    override fun getBestSellerProducts(): Flowable<List<ProductEntity>> {
        return productsCache.getBestSellerProducts()
    }

    override fun saveBestSellerProducts(bestSellerProducts: List<ProductEntity>): Completable {
        return productsCache.saveBestSellerProducts(bestSellerProducts)
            .andThen(productsCache.setLastCacheTime(System.currentTimeMillis()))
    }

    override fun clearBestSellerProducts(): Completable {
        return productsCache.clearBestSellerProducts()
    }

    override fun getProductsByCategoryId(categoryId: Int, offset: Int, limit: Int): Flowable<List<ProductEntity>> {
        throw UnsupportedOperationException("Get Products By Category Id isn't supported here...")
    }

    override fun postProductReservation(productId: Int): Completable {
        throw UnsupportedOperationException("Do Product Reservation isn't supported here...")
    }
}