package com.caio.lodjinha.viewmodel

import androidx.lifecycle.ViewModel
import com.caio.lodjinha.repository.ProductRepository

class ProductListViewModel(
    private val productRepository: ProductRepository) : ViewModel() {

    suspend fun getProductsByCategory(categoriaId: Int) =
        productRepository.getProductsByCategory(categoriaId)
}