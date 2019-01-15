package alodjinha.cfgdemelo.com.model

import com.google.gson.annotations.SerializedName

data class ProductsResponse(
    @SerializedName("data")
    val products: List<Product>
)

data class Product(
    @SerializedName("categoria")
    val category: ProductsCategory,
    @SerializedName("descricao")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("nome")
    val name: String,
    @SerializedName("precoDe")
    val priceFrom: Int,
    @SerializedName("precoPor")
    val priceFor: Double,
    @SerializedName("urlImagem")
    val imageUrl: String
)

data class ProductsCategory(
    @SerializedName("descricao")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("urlImagem")
    val imageUrl: String
)