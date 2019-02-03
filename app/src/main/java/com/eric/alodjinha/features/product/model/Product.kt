package com.eric.alodjinha.features.product.model

import com.eric.alodjinha.features.home.model.Category

data class Product(
    val id: Int,
    val descricao: String?,
    val urlImagem: String?,
    val nome: String?,
    val precoDe: Float?,
    val precoPor: Float?,
    val category: Category
)