package br.com.rbueno.lodjinha.model

import com.google.gson.annotations.SerializedName


data class Product(
    @SerializedName("precoDe")
    val oldPrice: Double,
    @SerializedName("categoria")
    val category: CategoryItem,
    @SerializedName("nome")
    val name: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("urlImagem")
    val urlImage: String,
    @SerializedName("precoPor")
    val newPrice: Double,
    @SerializedName("descricao")
    val description: String
)


data class ProductList(
    @SerializedName("data")
    val data: List<Product>
)

data class ProductPagedList(
    @SerializedName("data")
    val data: List<Product>,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("total")
    val total: Int
)


