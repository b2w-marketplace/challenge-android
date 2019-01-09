package br.com.android.seiji.data.store.product

import br.com.android.seiji.data.mapper.ProductMapper
import br.com.android.seiji.data.repository.product.ProductsCache
import br.com.android.seiji.domain.model.Product
import br.com.android.seiji.domain.repository.ProductRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class ProductsDataRepository @Inject constructor(
    private val mapper: ProductMapper,
    private val cache: ProductsCache,
    private val factory: ProductsDataStoreFactory
) : ProductRepository {

    override fun getBestSellerProducts(): Observable<List<Product>> {
        return Observable.zip(cache.areProductsCached().toObservable(),
            cache.isProductsCacheExpired().toObservable(),
            BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>> { areCached, isExpired ->
                Pair(areCached, isExpired)
            })
            .flatMap {
                factory.getDataStore(it.first, it.second)
                    .getBestSellerProducts().toObservable().distinctUntilChanged()
            }
            .flatMap { products ->
                factory.getCacheDataStore()
                    .saveBestSellerProducts(products)
                    .andThen(Observable.just(products))
            }
            .map {
                it.map {
                    mapper.mapFromEntity(it)
                }
            }
    }

    override fun getProductsByCategoryId(categoryId: Int, offset: Int, limit: Int): Observable<List<Product>> {
        return factory.getRemoteDataStore().getProductsByCategoryId(categoryId, offset, limit)
            .toObservable().distinctUntilChanged()
            .map {
                it.map {
                    mapper.mapFromEntity(it)
                }
            }
    }

    override fun doProductReservation(productId: Int): Completable {
        return factory.getRemoteDataStore().postProductReservation(productId)
    }
}