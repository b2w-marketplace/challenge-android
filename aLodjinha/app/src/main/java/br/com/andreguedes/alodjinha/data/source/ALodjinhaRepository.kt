package br.com.andreguedes.alodjinha.data.source

import br.com.andreguedes.alodjinha.data.model.BannerResponse
import br.com.andreguedes.alodjinha.data.model.CategoryResponse
import br.com.andreguedes.alodjinha.data.model.Product
import br.com.andreguedes.alodjinha.data.model.ProductResponse
import br.com.andreguedes.alodjinha.data.source.remote.ALodjinhaAPI
import br.com.andreguedes.alodjinha.data.source.remote.ALodjinhaService
import io.reactivex.Observable
import retrofit2.Response

class ALodjinhaRepository: ALodjinhaAPI {

    private var aLodjinhaService = ALodjinhaService()

    override fun getBanners(): Observable<BannerResponse> {
        return aLodjinhaService.getService().getBanners()
    }

    override fun getCategories(): Observable<CategoryResponse> {
        return aLodjinhaService.getService().getCategories()
    }

    override fun getProducts(offset: Int, limit: Int, categoriaId: Int): Observable<ProductResponse> {
        return aLodjinhaService.getService().getProducts(offset, limit, categoriaId)
    }

    override fun getProductsBestSellers(): Observable<ProductResponse> {
        return aLodjinhaService.getService().getProductsBestSellers()
    }

    override fun getProduct(produtoId: Int): Observable<Product> {
        return aLodjinhaService.getService().getProduct(produtoId)
    }

    override fun reserveProduct(produtoId: Int): Observable<Response<Void>> {
        return aLodjinhaService.getService().reserveProduct(produtoId)
    }

}