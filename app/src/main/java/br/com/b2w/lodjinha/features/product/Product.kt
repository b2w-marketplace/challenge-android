package br.com.b2w.lodjinha.features.product

import br.com.b2w.lodjinha.features.category.Category
import com.google.gson.annotations.SerializedName

data class Product(
    val id: Int,
    val urlImagem: String,
    @SerializedName("categoria") val category: Category,
    @SerializedName("descricao") val description: String,
    @SerializedName("nome") val name: String,
    @SerializedName("precoDe") val oldPrice: Double,
    @SerializedName("precoPor") val newPrice: Double
)