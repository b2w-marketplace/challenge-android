package br.com.andreguedes.alodjinha.data.model

import com.google.gson.annotations.SerializedName

data class Category(
        @field:SerializedName("id") val id: Int? = null,
        @field:SerializedName("descricao") val descricao: String? = null,
        @field:SerializedName("urlImagem") val urlImagem: String? = null
)

data class CategoryResponse(
        @field:SerializedName("data") val categoryList: List<Category>? = null
)