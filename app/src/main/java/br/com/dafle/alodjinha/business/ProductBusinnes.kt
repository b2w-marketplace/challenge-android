package br.com.dafle.alodjinha.business

import br.com.dafle.alodjinha.service.ProductService
import br.com.dafle.alodjinha.util.defaultThread

class ProductBusinnes(private val productService: ProductService) {

    fun all(offSet: Int, limit: Int, categoriaId: Int) = productService.all(offSet, limit, categoriaId).defaultThread()

    fun fetchBestSeller() = productService.fetchBestSeller().defaultThread()

    fun get(productId: Int) = productService.get(productId).defaultThread()

    fun reserve(productId: Int) = productService.reserve(productId).defaultThread()
}
