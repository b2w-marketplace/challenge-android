package br.com.android.seiji.remote

import br.com.android.seiji.data.model.ProductEntity
import br.com.android.seiji.data.repository.product.ProductsRemote
import br.com.android.seiji.remote.mapper.ProductsResponseModelMapper
import br.com.android.seiji.remote.service.LodjinhaService
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class ProductsRemoteImpl @Inject constructor(
    private val service: LodjinhaService,
    private val mapper: ProductsResponseModelMapper
) : ProductsRemote {

    override fun getBestSellerProducts(): Flowable<List<ProductEntity>> {
        return service.getBestSellerProducts()
            .map { it ->
                it.data.map {
                    mapper.mapFromModel(it)
                }
            }
    }

    override fun getProductsByCategoryId(categoryId: Int, offset: Int, limit: Int): Flowable<List<ProductEntity>> {
        return service.getProductsByCategoryId(categoryId, offset, limit)
            .map { it ->
                it.data.map {
                    mapper.mapFromModel(it)
                }
            }
    }

    override fun postProductReservation(productId: Int): Completable {
        return service.postProductReservation(productId)
    }

}