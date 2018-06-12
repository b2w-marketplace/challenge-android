package br.com.andreguedes.alodjinha.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Product(
        @field:SerializedName("id") val id: Int? = null,
        @field:SerializedName("nome") val nome: String? = null,
        @field:SerializedName("urlImagem") val urlImagem: String? = null,
        @field:SerializedName("descricao") val descricao: String? = null,
        @field:SerializedName("precoDe") val precoDe: Double? = null,
        @field:SerializedName("precoPor") val precoPor: Double? = null,
        @field:SerializedName("categoria") val categoria: Category? = null
): Serializable

data class ProductResponse(
        @field:SerializedName("data") val productList: List<Product>? = null
)