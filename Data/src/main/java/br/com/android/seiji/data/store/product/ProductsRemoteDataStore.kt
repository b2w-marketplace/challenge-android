package br.com.android.seiji.data.store.product

import br.com.android.seiji.data.model.ProductEntity
import br.com.android.seiji.data.repository.product.ProductsDataStore
import br.com.android.seiji.data.repository.product.ProductsRemote
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class ProductsRemoteDataStore @Inject constructor(
    private val productsRemote: ProductsRemote
) : ProductsDataStore {

    override fun getBestSellerProducts(): Flowable<List<ProductEntity>> {
        return productsRemote.getBestSellerProducts()
    }

    override fun getProductsByCategoryId(categoryId: Int, offset: Int, limit: Int): Flowable<List<ProductEntity>> {
        return productsRemote.getProductsByCategoryId(categoryId, offset, limit)
    }

    override fun postProductReservation(productId: Int): Completable {
        return productsRemote.doProductReservation(productId)
    }

    override fun saveBestSellerProducts(bestSellerProducts: List<ProductEntity>): Completable {
        throw UnsupportedOperationException("Save Best Seller Products isn't supported here...")
    }

    override fun clearBestSellerProducts(): Completable {
        throw UnsupportedOperationException("Clear Best Seller Products isn't supported here...")
    }
}