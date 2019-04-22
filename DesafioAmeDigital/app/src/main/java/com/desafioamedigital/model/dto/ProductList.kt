package com.desafioamedigital.model.dto

import com.google.gson.annotations.SerializedName

data class ProductList(
    @SerializedName("data")
    val productList: List<Product>
)

data class Product(
    @SerializedName("categoria")
    val category: Category,
    @SerializedName("id")
    val id: Int,
    @SerializedName("descricao")
    val description: String,
    @SerializedName("nome")
    val name: String,
    @SerializedName("precoDe")
    val priceFrom: Double,
    @SerializedName("precoPor")
    val priceTo: Double,
    @SerializedName("urlImagem")
    val imageUrl: String
)
