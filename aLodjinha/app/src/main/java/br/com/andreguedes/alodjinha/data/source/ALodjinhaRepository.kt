package br.com.andreguedes.alodjinha.data.source

import br.com.andreguedes.alodjinha.data.model.Banner
import br.com.andreguedes.alodjinha.data.model.Category
import br.com.andreguedes.alodjinha.data.model.Product
import br.com.andreguedes.alodjinha.data.source.remote.ALodjinhaAPI
import br.com.andreguedes.alodjinha.data.source.remote.ALodjinhaService
import io.reactivex.Observable

class ALodjinhaRepository(
        val aLodjinhaService: ALodjinhaService
) : ALodjinhaAPI {

    override fun getBanners(): Observable<List<Banner>> {
        return aLodjinhaService.getService().getBanners()
    }

    override fun getCategories(): Observable<List<Category>> {
        return aLodjinhaService.getService().getCategories()
    }

    override fun getProducts(): Observable<List<Product>> {
        return aLodjinhaService.getService().getProducts()
    }

    override fun getProductsBestSellers(): Observable<List<Product>> {
        return aLodjinhaService.getService().getProductsBestSellers()
    }

    override fun getProduct(produtoId: Int): Observable<Product> {
        return aLodjinhaService.getService().getProduct(produtoId)
    }

    override fun reserveProduct(produtoId: Int): Observable<Void> {
        return aLodjinhaService.getService().reserveProduct(produtoId)
    }

}